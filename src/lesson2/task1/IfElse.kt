@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

// Урок 2: ветвления (здесь), логический тип (см. 2.2).
// Максимальное количество баллов = 6
// Рекомендуемое количество баллов = 5
// Вместе с предыдущими уроками = 9/12

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая (2 балла)
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    //Берём только решающую часть возраста, так как сотни не влияют
    val decisivePartOfAge = age % 100
    //И запоминаем последнюю цифру возраста как строку
    val ageLastNumberToStr = age.toString().takeLast(1)
    return when {
        //От попадания в возраст от 10 до 20 защищают дополнительные условия
        ageLastNumberToStr == "1" && decisivePartOfAge != 11 -> {
            "$age год"
        }
        ageLastNumberToStr in "567890" || (decisivePartOfAge > 10 && decisivePartOfAge < 20) -> {
            "$age лет"
        }
        else -> {
            "$age года"
        }

    }
}

/**
 * Простая (2 балла)
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val s1 = t1 * v1
    val s2 = t2 * v2
    val s3 = t3 * v3
    val wholeWay: Double = s1 + s2 + s3
    val halfWay: Double = wholeWay / 2
    when {
        //Если мы двигались двигались первую половину пути с одной скоростью, то это v1, и решение простое
        s1 >= s2 + s3 -> return halfWay / v1
        //Если к половине пути мы двигались с двумя скоростями
        //то мы вычисляем нужную нам часть от t2, деля путь, пройденный с начала v2 до середины пути, на саму v2
        s1 + s2 >= s3 -> return t1 + (halfWay - s1) / v2

        //Если к середине пути мы двигаемся уже с третьей скоростью,
        //то мы так же берем от t3 только нужную нам часть до середины
        else -> return t1 + t2 + (halfWay - s1 - s2) / v3
    }

}

/**
 * Простая (2 балла)
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    //Проверяем угрозу от каждой ладьи и запоминаем в переменную
    val underThreatOf1 = kingX == rookX1 || kingY == rookY1
    val underThreatOf2 = kingX == rookX2 || kingY == rookY2
    return when {
        //Оцениваем угрозы и выдаём ответ
        underThreatOf1 && underThreatOf2 -> 3
        !underThreatOf1 && underThreatOf2 -> 2
        underThreatOf1 && !underThreatOf2 -> 1
        else -> 0
    }
}


/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    val underThreatOfRook = kingX == rookX || kingY == rookY
    //Угрозу по диагонали проверяем сравнивая суммы координат слона и короля
    val underThreatOfBishop = when {
        kingX + kingY == bishopX + bishopY -> true
        else -> {
            //Отражаем координаты доски по оси Y, чтобы проверить по второй диагонали таким же способом
            val invertedSumOfKing = kingX + (11 - kingY)
            val invertedSumOfBishop = bishopX + (11 - bishopY)
            invertedSumOfBishop == invertedSumOfKing
        }
    }
    return when {
        underThreatOfBishop && underThreatOfRook -> 3
        underThreatOfBishop && !underThreatOfRook -> 2
        !underThreatOfBishop && underThreatOfRook -> 1
        else -> 0
    }
}

/**
 * Простая (2 балла)
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    val abc = listOf(a, b, c).sorted()
    return when {
        abc[0] + abc[1] < abc[2] -> -1
        sqr(abc[0]) + sqr(abc[1]) > sqr(abc[2]) -> 0
        sqr(abc[0]) + sqr(abc[1]) < sqr(abc[2]) -> 2
        else -> 1
    }
}

/**
 * Средняя (3 балла)
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int = when {
    a >= c && b >= d -> if (a <= d) d - a else -1
    a >= c && b <= d -> b - a
    a <= c && b >= d -> d - c
    a <= c && b <= d -> if (b >= c) b - c else -1
    else -> -1
}
