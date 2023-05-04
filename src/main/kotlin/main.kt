fun main() {
    val typeCard: String = "Visa"
    var resultPay: Double = sendMoney(typeCard, 50000000.0, 1000.0)

    when (resultPay) {
        -1.0 -> println("Превышен лимит переводов")
        -2.0 -> println("Данный вид карты не обслуживается")
        else -> println("Перевод вместе с комиссией составит: $resultPay")
    }
}

fun sendMoney(typeCard: String, previousMoneyOrder: Double = 0.0, currentMoneyOrder: Double): Double {
    val maxLimitInDay = 150_000
    val maxLimitInMonth = 600_000

    val maxLimitInDayVKPay = 15_000
    val maxLimitInMonthVKPay = 40_000

    var moneyForPay = 0.0

    when (typeCard) {
        "MasterCard", "Maestro" -> {
            moneyForPay = ((currentMoneyOrder * 0.006) + 20) + currentMoneyOrder

            if (moneyForPay > maxLimitInDay || (moneyForPay + previousMoneyOrder) > maxLimitInMonth) {
                return -1.0
            }
            return moneyForPay
        }

        "Visa", "Мир" -> {
            moneyForPay = ((currentMoneyOrder * 0.0075) + 35) + currentMoneyOrder

            if (moneyForPay > maxLimitInDay || (moneyForPay + previousMoneyOrder) > maxLimitInMonth) {
                return -1.0
            }
            return moneyForPay
        }

        "VKPay" -> {
            moneyForPay = currentMoneyOrder

            if (moneyForPay > maxLimitInDayVKPay || (moneyForPay + previousMoneyOrder) > maxLimitInMonthVKPay) {
                return -1.0
            }
            return moneyForPay
        }

        else -> {
            return -2.0
        }
    }
}
