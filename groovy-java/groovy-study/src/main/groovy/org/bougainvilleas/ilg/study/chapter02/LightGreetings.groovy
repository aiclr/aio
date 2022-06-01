package org.bougainvilleas.ilg.study.chapter02

for (int i = 0; i < 3; i++)
{
    System.out.print(i + "ho ");
}
System.out.println("Merry Groovy!")

for (i in 0..2) {print i + 'ho' + "$i" + ' '}
println 'Merry Groovy!'

0.upto(2) { print it + "ho$it " }
println 'Merry Groovy!'


3.times { print it + "ho$it " }
println 'Merry Groovy!'


0.step(10,4) { print it + "ho$it " }
println 'Merry Groovy!'