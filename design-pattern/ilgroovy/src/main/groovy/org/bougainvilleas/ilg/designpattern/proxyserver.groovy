package org.bougainvilleas.ilg.designpattern

class Accumulator {
    def accumulate(args) {
        args.inject(0) { total, arg ->
            total += arg
        }
    }
}

def port = 54321
def accumulator = new Accumulator()
def server = new ServerSocket(port)
println "Starting server on port $port"

while (true) {
    server.accept() { socket ->
        socket.withObjectStreams { ois, oos ->
            def args = ois.readObject()
            oos << accumulator.accumulate(args)
        }
    }
}
