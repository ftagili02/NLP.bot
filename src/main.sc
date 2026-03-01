import com.justai.jaicf.builder.BotBuilder
import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.http.asHttpBotRequest
import com.justai.jaicf.channel.jaicp.JaicpBotChat
import com.justai.jaicp.channel.jaicp.JaicpChatApiBotChannel
import com.justai.jaicf.context.BotContext
import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.api.hasQuery
import com.justai.jaicf.reactions.Reactions

val mainScenario = Scenario {
    
    state("/hello") {
        activators {
            regex("привет|здравствуй|hello|hi|добрый", RegexOption.IGNORE_CASE)
            intent("/hello")
        }
        
        action {
            reactions.sayRandom(
                "Привет! Чем могу помочь?",
                "Здравствуйте! Рад вас видеть!",
                "Приветствую! Готов ответить на ваши вопросы."
            )
        }
    }
    
    state("/weather") {
        activators {
            regex("погода|прогноз|температура|weather|forecast", RegexOption.IGNORE_CASE)
            intent("/weather")
        }
        
        action {
            reactions.sayRandom(
                "Сейчас солнечно, температура +20°C",
                "Сегодня облачно, возможен дождь. Температура +18°C",
                "Погода отличная! +22°C и ясно",
                "Сегодня прохладно, около +15°C"
            )
        }
    }
    
    state("/currency") {
        activators {
            regex("курс|валюта|доллар|евро|рубль|currency|exchange rate", RegexOption.IGNORE_CASE)
            intent("/currency")
        }
        
        action {
            reactions.sayRandom(
                "Курс доллара: 92.50 руб., евро: 99.80 руб.",
                "На сегодня: USD - 92.45 руб., EUR - 99.75 руб.",
                "Актуальный курс: доллар 92.55 руб., евро 99.85 руб."
            )
        }
    }
    
    state("/NoMatch") {
        activators {
            intent("/NoMatch")
            catchAll()
        }
        
        action {
            reactions.sayRandom(
                "Извините, я не понял ваш вопрос. Попробуйте спросить о погоде или курсе валют.",
                "Я пока не могу ответить на этот вопрос. Спросите меня о погоде или валюте.",
                "Не совсем понял. Я могу рассказать о погоде или сообщить курс валют."
            )
        }
    }
}

fun createBot() = BotBuilder {
    addScenario(mainScenario)
}