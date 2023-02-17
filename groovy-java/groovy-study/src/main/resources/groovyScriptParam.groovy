/**
 * engine.put("who", name);
 * engine.put("age", age);
 */

def check(age) {
    println "$who can ${(age > 6 && age < 18) ? '' : 'not '}go to school at the age of $age"
    this
}

check age
