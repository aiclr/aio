package org.bougainvilleas.ilg.designpattern

/**
 * State Pattern
 * 状态模式
 */
class ClientState {
    def context

    ClientState(context) {
        this.context = context
        inform()
    }
}

class Offline extends ClientState {
    Offline(context) {
        super(context)
    }

    def inform() {
        println "offline"
    }

    def connect() {
        context.state = new Online(context)
    }

    def disconnect() {
        println "error: not connected"
    }

    def send_message(message) {
        println "error: not connected"
    }

    def receive_message() {
        println "error: not connected"
    }
}

class Online extends ClientState {
    Online(context) {
        super(context)
    }

    def inform() {
        println "connected"
    }

    def connect() {
        println "error: already connected"
    }

    def disconnect() {
        context.state = new Offline(context)
    }

    def send_message(message) {
        println "\"$message\" sent"
    }

    def receive_message() {
        println "message received"
    }
}

class Context {
    def state = new Offline(this)
}

class Client2 {
    def context = new Context()

    def connect() {
        context.state.connect()
    }

    def disconnect() {
        context.state.disconnect()
    }

    def send_message(message) {
        context.state.send_message(message)
    }

    def receive_message() {
        context.state.receive_message()
    }
}

client = new Client2()
client.send_message("Hello")
client.connect()
client.send_message("Hello")
client.connect()
client.receive_message()
client.disconnect()


/**
 * Variation 1: Leveraging Interface-Oriented Design
 * 变体1：利用面向接口的设计
 */
println '\n变体1：利用面向接口的设计'

interface State {
    def connect()

    def disconnect()

    def send_message(message)

    def receive_message()
}

class Context2 {
    def state = new Offline2(this)
}

class Client3 implements State {
    def context = new Context2()

    def connect() {
        context.state.connect()
    }

    def disconnect() {
        context.state.disconnect()
    }

    def send_message(message) {
        context.state.send_message(message)
    }

    def receive_message() {
        context.state.receive_message()
    }
}

class Offline2 extends ClientState implements State {
    Offline2(context) {
        super(context)
    }

    def inform() {
        println "offline"
    }

    def connect() {
        context.state = new Online2(context)
    }

    def disconnect() {
        println "error: not connected"
    }

    def send_message(message) {
        println "error: not connected"
    }

    def receive_message() {
        println "error: not connected"
    }
}

class Online2 extends ClientState implements State {
    Online2(context) {
        super(context)
    }

    def inform() {
        println "connected"
    }

    def connect() {
        println "error: already connected"
    }

    def disconnect() {
        context.state = new Offline2(context)
    }

    def send_message(message) {
        println "\"$message\" sent"
    }

    def receive_message() {
        println "message received"
    }
}

client = new Client3()
client.send_message("Hello")
client.connect()
client.send_message("Hello")
client.connect()
client.receive_message()
client.disconnect()


/**
 * Variation 2: Extract State Pattern Logic
 * 变体2：提取状态模式逻辑
 */
println '\n变体2：提取状态模式逻辑'

abstract class InstanceProvider {
    static def registry = GroovySystem.metaClassRegistry

    static def create(objectClass, param) {
        /**
         * initialState -> Offline3 [objectClass]
         * this -> Client4 [param]
         *
         *  registry.getMetaClass(Offline3.class).invokeConstructor([Client4])
         *
         *  相当于给 Offline3 增加构造器
         *  Offline3(Context3 context) {super(context)}
         */
        registry.getMetaClass(objectClass).invokeConstructor([param] as Object[])
    }
}

abstract class Context3 {
    private context

    protected setContext(context) {
        this.context = context
    }

    def invokeMethod(String name, Object arg) {
        context.invokeMethod(name, arg)
    }

    def startFrom(initialState) {
        setContext(InstanceProvider.create(initialState, this))
    }
}

abstract class State2 {
    private client

    State2(client) { this.client = client }

    def transitionTo(nextState) {
        client.setContext(InstanceProvider.create(nextState, client))
    }
}

class Client4 extends Context3 {
    Client4() {
        startFrom(Offline3)
    }
}

class Offline3 extends State2 {
    Offline3(client) {
        super(client)
        println "Offline3"
    }

    def connect() {
        transitionTo(Online3)
    }

    def disconnect() {
        println "error: not connected"
    }

    def send_message(message) {
        println "error: not connected"
    }

    def receive_message() {
        println "error: not connected"
    }
}

class Online3 extends State2 {

    Online3(Object client) {
        super(client)
        println "Online3"
    }

    def connect() {
        println "error: already connected"
    }

    def disconnect() {
        transitionTo(Offline3)
    }

    def send_message(message) {
        println "\"$message\" sent"
    }

    def receive_message() {
        println "message received"
    }
}

client = new Client4()
client.send_message("Hello")
client.connect()
client.send_message("Hello")
client.connect()
client.receive_message()
client.disconnect()

/**
 * Variation 3: Bring on the DSL
 * 变体3：启用DSL
 */
println '\n变体3：启用DSL'

class Grammar {
    def fsm
    def event
    def fromState
    def toState

    Grammar(a_fsm) {
        fsm = a_fsm
    }

    def on(a_event) {
        event = a_event
        this
    }

    def on(a_event, a_transitioner) {
        on(a_event)
        a_transitioner.delegate = this
        a_transitioner.call()
        this
    }

    def from(a_fromState) {
        fromState = a_fromState
        this
    }

    def to(a_toState) {
        assert a_toState, "Invalid toState: $a_toState"
        toState = a_toState
        fsm.registerTransition(this)
        this
    }

    def isValid() {
        event && fromState && toState
    }

    public String toString() {
        "$event: $fromState=>$toState"
    }
}

class FiniteStateMachine {
    def transitions = [:]
    def initialState
    def currentState

    FiniteStateMachine(a_initialState) {
        assert a_initialState, "You need to provide an initial State"
        initialState = a_initialState
        currentState = a_initialState
    }

    def record() {
        Grammar.newInstance(this)
    }

    def reset() {
        currentState = initialState
    }

    def isState(a_state) {
        currentState == a_state
    }

    def registerTransition(a_grammar) {
        assert a_grammar.isValid(), "Invalid transition ($a_grammar)"
        def transition
        def event = a_grammar.event
        def fromState = a_grammar.fromState
        def toState = a_grammar.toState
        if (!transitions[event]) {
            transitions[event] = [:]
        }
        transition = transitions[event]
        assert !transition[fromState], "Duplicate fromState $fromState for transition $a_grammar"
        transition[fromState] = toState
    }

    def fire(a_event) {
        assert currentState, "Invalid current state '$currentState':passed init constructor"
        assert transitions.containsKey(a_event), "Invalid event '$a_event', should be one of ${transitions.ketSet()}"
        def transition = transitions[a_event]
        def nextState = transition[currentState]
        assert nextState, "There is no transition from '$currentState' to any other state"
        currentState = nextState
        currentState
    }
}

class StatePatternDslTest extends GroovyTestCase {
    private fsm

    protected void setUp() {
        fsm = FiniteStateMachine.newInstance('offline')
        def recorder = fsm.record()
        recorder.on('connect').form('offline').to('online')
        recorder.on('disconnect').form('online').to('offline')
        recorder.on('send_message').form('online').to('online')
        recorder.on('receive_message').form('online').to('online')
    }

    void testInitialState() {
        assert fsm.isState('offline')
    }

    void testOfflineState() {
        shouldFail {
            fsm.fire('send_message')
        }
        shouldFail {
            fsm.fire('receive_message')
        }
        shouldFail {
            fsm.fire('disconnect')
        }
        assert 'online' == fsm.fire('connect')
    }

    void testOnlineState() {
        fsm.fire('connect')
        fsm.fire('send_message')
        fsm.fire('receive_message')
        shouldFail {
            fsm.fire('connect')
        }
        assert 'offline' == fsm.fire('disconnect')
    }
}