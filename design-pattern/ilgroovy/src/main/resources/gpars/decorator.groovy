@Grab('org.codehaus.gpars:gpars:0.10')
import static groovyx.gpars.GParsPool.withPool

interface Document {
    void print()
    String getText()
}

class DocumentImpl implements Document {
    def document
    void print() { println document }
    String getText() { document }
}

def words(String text) {
    text.replaceAll('[^a-zA-Z]', ' ').trim().split("\\\\s+")*.toLowerCase()
}

def avgWordLength = {
    def words = words(it.text)
    sprintf "Avg Word Length: %4.2f", words*.size().sum() / words.size()
}
def modeWord = {
    def wordGroups = words(it.text).groupBy {it}.collectEntries { k, v -> [k, v.size()] }
    def maxSize = wordGroups*.value.max()
    def maxWords = wordGroups.findAll { it.value == maxSize }
    "Mode Word(s): ${maxWords*.key.join(', ')} ($maxSize occurrences)"
}
def wordCount = { d -> "Word Count: " + words(d.text).size() }

def asyncDecorator(Document d, Closure c) {
    ProxyGenerator.INSTANCE.instantiateDelegate([print: {
        withPool {
            def result = c.callAsync(d)
            d.print()
            println result.get()
        }
    }], [Document], d)
}

Document d = asyncDecorator(asyncDecorator(asyncDecorator(
        new DocumentImpl(document:"This is the file with the words in it\\n\\t\\nDo you see the words?\\n"),
//        new DocumentImpl(document: new File('AsyncDecorator.groovy').text),
        wordCount), modeWord), avgWordLength)
d.print()

//run with `groovy decorator.groovy`