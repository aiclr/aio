package org.bougainvilleas.ilg.designpattern

/**
 * 猜数字游戏
 */
class GuessGameMessage {
    def welcome = 'Welcome to the guessing game, my secret number is between 1 and 100'
    def done = 'Correct'
}

class GuessGameInputConverter {
    def convert(input) { input.toInteger() }
}

class GuessGameControl {
    private lower = 1
    private upper = 100
    private guess = new Random().nextInt(upper - lower) + lower

    def moreTurns() {
        def done = (lower == guess || upper == guess)
        if (!done) {
            println "Enter a number between $lower and $upper"
        }
        !done
    }

    def play(nextGuess) {
        if (nextGuess <= guess) {
            lower = [lower, nextGuess].max()
        }
        if (nextGuess >= guess) {
            upper = [upper, nextGuess].min()
        }
    }
}

/**
 * 猜双游戏 两个偶赢 两奇数输 一奇一偶平局
 */
class TwoupMessages {
    def welcome = 'Welcome to the twoup game,you start with $1000'
    def done = 'Sorry,you have no money left, goodbye'
}

class TwoupInputConverter {
    def convert(input) { input.toInteger() }
}

class TwoupControl {
    private money = 1000
    private random = new Random()

    private tossWasHead() {
        def next = random.nextInt()
        return next % 2 == 0
    }

    def moreTurns() {
        if (money > 0) {
            println "You have $money, how much would you like to bet?"
            return true
        }
        false
    }

    def play(amount) {
        def coin1 = tossWasHead()
        def coin2 = tossWasHead()
        if (coin1 && coin2) {
            money += amount
            println 'You win'
        } else if (!coin1 && !coin2) {
            money -= amount
            println 'You lose'
        } else {
            // 平局
            println 'Draw'
        }
    }
}

// 具体工厂
def guessFactory = [messages: GuessGameMessage, control: GuessGameControl, converter: GuessGameInputConverter]
def twoupFactory = [messages: TwoupMessages, control: TwoupControl, converter: TwoupInputConverter]

// 抽象工厂
class GameFactory {
    def static factory

    def static getMessages() { return factory.messages.newInstance() }

    def static getControl() { return factory.control.newInstance() }

    def static getConverter() { return factory.converter.newInstance() }
}

// 测试
GameFactory.factory =twoupFactory
//GameFactory.factory = guessFactory

def messages = GameFactory.messages
def control = GameFactory.control
def converter = GameFactory.converter
println messages.welcome
def reader = new BufferedReader(new InputStreamReader(System.in))
while (control.moreTurns()) {
    def input = reader.readLine().trim()
    control.play(converter.convert(input))
}
println messages.done