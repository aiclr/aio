package org.bougainvilleas.ilg.study.chapter16.eam


@org.bougainvilleas.ilg.study.chapter16.eam.EAM
class Resource {
    private def open() { print "opened..." }
    private def close() { print "closed" }
    def read() { print "read..." }
    def write() { print "write..." }
}
println "Using Resource"
Resource.use  {
    read()
    write()
}