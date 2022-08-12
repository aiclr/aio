package org.bougainvilleas.ilg.study.chapter19

import org.bougainvilleas.ilj.study.chapter19.PizzaShop

def getPizza(closure){
    PizzaShop pizzaShop=new PizzaShop()
    closure.delegate=pizzaShop
    closure()
}

time=getPizza{
    setSize 'large'
    setCrust 'thin'
    setTopping "Olives","Onions","Bell Pepper"
    setAddress "101 Main St.,..."
    setCard('Visa',"1234-1234-1234-1234")
}
printf"Pizza will arrive in %d minutes\n",time

