package org.bougainvilleas.ilg.study.chapter05

memo = '''line1
line2
line3
line4
'''
println memo

price = 251.12
message = """line1
line2\$$price
line3
line4
"""
println message


langs = ['C++': 'Stroustrup', 'Java': 'Gosling', 'Lisp': 'McCarthy']
content = ''
langs.each { language, author ->
    fragment =
"""
  <language name="${language}">
    <author>${author}</author>
  </language>
"""
    content += fragment
}
xml = "<languages>${content}</language>"
println xml