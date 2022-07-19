package org.bougainvilleas.ilg.study.chapter08

langs = ['C++': 'Stroustrup', 'Java': 'Gosling', 'Lisp': 'McCarthy']

content = ''

langs.each { language, author ->
    fragment = """
   <language name="${language}">
        <author>${author}</author>
   </language>
"""
    content += fragment
}
xml = "<languages>$content</languages>"
println xml