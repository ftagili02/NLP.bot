// Основной файл бота (Bot.kt)

import com.justai.jaicf.builder.BotBuilder
import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.http.asHttpBotRequest
import com.justai.jaicf.channel.jaicp.JaicpBotChat
import com.justai.jaicp.channel.jaicp.JaicpChatApiBotChannel
import com.justai.jaicf.context.BotContext
import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.api.hasQuery
import com.justai.jaicf.reactions.Reactions

// Создание сценария бота
val mainScenario = Scenario {
    
    // Состояние /hello - приветствие
    state("/hello") {
        activators {
            regex("привет|здравствуй|hello|hi|добрый")
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
    
    // Состояние /weather - прогноз погоды
    state("/weather") {
        activators {
            regex("погода|прогноз|температура|weather|forecast")
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
    
    // Состояние /currency - курс валют
    state("/currency") {
        activators {
            regex("курс|валюта|доллар|евро|рубль|currency|exchange rate")
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
    
    // Состояние /NoMatch - обработка неизвестных интентов
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

// Функция для создания бота
fun createBot() = BotBuilder {
    addScenario(mainScenario)
}