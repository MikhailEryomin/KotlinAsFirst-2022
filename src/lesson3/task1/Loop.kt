@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import kotlin.math.*

// Урок 3: циклы
// Максимальное количество баллов = 9
// Рекомендуемое количество баллов = 7
// Вместе с предыдущими уроками = 16/21

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая (2 балла)
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 0
    var number = n
    if (number != 0) {
        while (abs(number) > 0) {
            count += 1
            number /= 10
        }
    } else count = 1
    return count
}

/**
 * Простая (2 балла)
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
//    if (n == 1) return 1
//    if (n == 2) return 1
//    return fib(n - 2) + fib(n - 1)
    var n0 = 0
    var n1 = 1
    var i = 2
    while (i <= n) {
        val n2 = n0 + n1
        n0 = n1
        n1 = n2
        i++
    }
    return n1
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    val minDel = Int.MAX_VALUE
    for (d in 2..round(sqrt(n.toDouble())).toInt() + 1) {
        if (n % d == 0) {
            return minOf(minDel, d)
        }
    }
    return n
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var maxDel = 1
    for (d in 2..sqrt(n.toDouble()).toInt()) {
        if (n % d == 0) maxDel = maxOf(maxDel, d, n / d)
    }
    return maxDel
}

/**
 * Простая (2 балла)
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var steps = 0
    var num = x
    while (num != 1) {
        if (num % 2 == 0) num /= 2
        else num = num * 3 + 1
        steps++
    }
    return steps
}

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun nod (a: Int, b: Int): Int {
    var m1 = a
    var n1 = b
    while (m1 != n1) {
        if (m1 > n1) m1 -= n1
        else n1 -= m1
    }
    return m1
}
fun lcm(m: Int, n: Int): Int {
    //НОД
    val nod_= nod(m, n)
    //НОК
    return n * m / nod_
}

/**
 * Средняя (3 балла)
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean =
    when (nod(m, n)) {
        1 -> true
        else -> false
    }

/**
 * Средняя (3 балла)
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var newNum = 0
    var num = n
    while (num > 0) {
        newNum = newNum * 10 + num % 10
        num /= 10
    }
    return newNum
}

/**
 * Средняя (3 балла)
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean =
    when (revert(n)) {
        n -> true
        else -> false
    }

/**
 * Средняя (3 балла)
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val veryLastDig = n % 10
    var x = n / 10
    while (x > 0) {
        val lastDig = x % 10
        if (lastDig != veryLastDig) return true
        x /= 10
    }
    return false
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var result = 0.0
    val x1 = x % (2 * PI)
    var part: Double
    var sign = 1
    for (n in 1..100 step 2) {
        part = x1.pow(n) / factorial(n)
        result += sign * part
        if (abs(part) < eps) return result
        sign *= -1
    }
    return 0.0
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var result = 1.0
    var part: Double
    val x1 = x % (2 * PI)
    var sign = -1
    for (n in 2..100 step 2) {
        part = x1.pow(n) / factorial(n)
        result += sign * part
        if (abs(part) < eps) return result
        sign *= -1
    }
    return 0.0
}

/**
 * Сложная (4 балла)
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var sequence: Int
    var total = 0
    for (m in 1..n) {
        //Подсчёт количества цифр в записи квадрата числа m
        val forNext = digitNumber(sqr(m))
        //в эту переменную записываем значение отдельного члена пос-ти
        sequence = sqr(m)
        //счётчик цифр в последовательности
        total += forNext
        if (total > n) return (sequence / 10.0.pow(total - n) % 10).toInt()
        if (total == n) return sequence % 10
    }
    return 0

}

/**
 * Сложная (5 баллов)
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var sequence: Int
    var total = 0
    for (m in 1..n) {
        //Подсчёт количества цифр в записи числа Фиббоначи
        val nextFib = fib(m)
        val forNext = digitNumber(nextFib)
        //в эту переменную записываем значение отдельного члена пос-ти
        sequence = nextFib
        //счётчик цифр в последовательности
        total += forNext
        if (total > n) return (sequence / 10.0.pow(total - n) % 10).toInt()
        if (total == n) return sequence % 10
    }
    return 0
}
