##  Spring Boot ilə OpenAI nasıl kullanırız?

### Model 
Model bir makine öğrenimi modelidir.
Model, verileri tahmin etmek, sınıflandırmak, gruplandırmak, analiz etmek veya diğer görevler için tasarlanmış bir makine öğrenimi algoritmasıdır.

### Max Token
Belirteçler, metindeki kelime, simge veya diğer anlamlı birimlerdir. Eğer "Max Token" değeri düşürülürse, model daha kısa bir metin üretecektir ve tersine, eğer "Max Token" değeri yükseltilirse, model daha uzun bir metin üretecektir.

### Temperature
GPT-3'teki sıcaklık parametresi, model çıktılarının rastgeleliğini veya yaratıcılığını kontrol eden bir hiperparametredir.

Daha düşük sıcaklıklar daha ölçülü ve öngörülebilir çıktılar sağlarken, daha yüksek sıcaklıklar daha yaratıcı ve çeşitli çıktılar sağlar.

### Top P
Eğer "p" değeri düşürülürse, daha önce öğrenilen veya mevcut veriler tarafından desteklenen sonuçlar daha yüksek bir şansla seçilecektir ve metin daha az yaratıcı olacaktır. Eğer "p" değeri yükseltilirse, model daha az önce öğrenilen verileri referans alacak ve daha fazla rastgele ve yaratıcı sonuçlar üretecektir.


[Daha fazlası için bu siteyi ziyaret ede bilirsiniz](https://platform.openai.com/docs/api-reference/completions/)

- [x] Ilk olarak bu siteye ziyaret etdigimizde text-davinci-003 modelini kullanarak nasil istek atmali olduğumuzu yazıyor
```curl
curl https://api.openai.com/v1/completions \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer YOUR_API_KEY' \
  -d '{
  "model": "text-davinci-003",
  "prompt": "Say this is a test",
  "max_tokens": 7,
  "temperature": 0
}'
```
- Burada gördüğümüz gibi Content-Type :"application/json" atılan istek auth bir istek olmalı yani kayıt olusturup bir token ile istek atmalıyız
- Ve diğer parametreler zaten dokumantasyona baktiğınızda da gorüceksiniz model parametresi zorunlu bir parametre

- [x] Cevap olarak da bu sekilde bir JSON objesi doneceğini gostermiş
```json
{
  "id": "cmpl-uqkvlQyYK7bGYrRHQ0eXlWi7",
  "object": "text_completion",
  "created": 1589478378,
  "model": "text-davinci-003",
  "choices": [
    {
      "text": "\n\nThis is indeed a test",
      "index": 0,
      "logprobs": null,
      "finish_reason": "length"
    }
  ],
  "usage": {
    "prompt_tokens": 5,
    "completion_tokens": 7,
    "total_tokens": 12
  }
}
```

### Spring Boot ile nasıl uygularım

1.
```java
public HttpEntity<ChatGptRequest> buildHttpEntity(ChatGptRequest chatGptRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(MediaType.parseMediaTypes((ConstantForChatGpt.MEDIA_TYPE)));
        httpHeaders.set(ConstantForChatGpt.AUTHORIZATION, ConstantForChatGpt.BEARER + ConstantForChatGpt.API_KEY);
        return new HttpEntity<ChatGptRequest>(chatGptRequest, httpHeaders);
    }
```
2.
```java
public ChatGptResponse response(HttpEntity<ChatGptRequest> gptRequestHttpEntity) {
ResponseEntity<ChatGptResponse> response = restTemplate.postForEntity(ConstantForChatGpt.URL,
gptRequestHttpEntity, ChatGptResponse.class);
return response.getBody();
}
```
3.
```java
public ChatGptResponse askQuestion(ClientRequest clientRequest) {
        ChatGptRequest chatGptRequest = clientRequestConvertToChatGptRequest(clientRequest);
        HttpEntity<ChatGptRequest> httpEntity = buildHttpHeaders(chatGptRequest);
        return response(httpEntity);
    }

    
    private ChatGptRequest clientRequestConvertToChatGptRequest(ClientRequest clientRequest) {
        return new ChatGptRequest(
                ConstantForChatGpt.MODEL,
                clientRequest.getMessage(),
                ConstantForChatGpt.TEMPERATURE,
                ConstantForChatGpt.MAX_TOKEN,
                ConstantForChatGpt.TOP_P
        );
    }
```


1. Yukarıda dediğimiz şeyi yaptık  "Content-Type: application/json" ve "Authorization" başlığını ekledik. Bu başlıkta, Bearer ile bize verilen tokeni atadık.
2. Burada kullandığımız RestTemplate spring boot üzerinden Restful servislere ulaşıp response almamızı sağlar. Yanı  client olarak bir servisten veri çekebiliriz.
  - [✔️] İstek yapmak istediğiniz URL'yi belirttikten sonra, bu isteğin bir POST isteği olduğunu belirttik. Daha sonra, RestTemplate belirttiğiniz dönüş tipine göre veriyi otomatik olarak map edecektir. İşlem yaparken Reflection teknolojisini kullanır ve gelen veriyi belirttiğiniz dönüş tipine göre yapılandırır
3. Son olarak, kullanıcıdan gelen isteği, ChatGPT için oluşturduğumuz Request modeline dönüştürüp, RESTful servise bir istek gönderiyoruz.

### Umarım size faydalı olmuştur :)

