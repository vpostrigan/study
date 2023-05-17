// Функции

// Объявление и вызов функций
// Обычно параметры функции аннотируются явно
// Возвращаемый тип подлежит выводу
function add(a: number, b: number) {
    return a + b;
}

// в JavaScript и TypeScript предлагается как минимум пять способов объявления функции:
// Именованная функция
function greet(name: string) {
    return 'hello ' + name
}
// Функциональное выражение
let greet2 = function (name: string) {
    return 'hello ' + name;
}
// Выражение стрелочной функции
let greet3 = (name: string) => {
    return 'hello ' + name
}
// Сокращенное выражение стрелочной функции
let greet4 = (name: string) =>
    'hello ' + name;
// Конструктор функции (не рекомендуется использовать)
let greet5 = new Function('name', 'return "hello " + name')


// Параметр — это часть данных, необходимых функции для запуска, объявленная как часть декларации этой функции.
// Также может называться формальным параметром.
// Аргумент — это часть данных, предаваемая функции при ее вызове.
// Также может называться актуальным параметром.

// При вызове функции в TypeScript не нужно предоставлять дополнительную информацию о типе — достаточно передать ей некий аргумент,
// и TypeScript проверит совместимости этого аргумента с типами параметров функции:
add(1, 2)        // вычисляется как 3
greet('Crystal') // выводится как 'hello Crystal'
// Если вы забыли аргумент или передали аргумент неверного типа, TypeScript поспешит на это указать:
add(1)              // Ошибка TS2554: ожидается 2 аргумента, но получен 1.
add(1, 'a')      // Ошибка TS2345: аргумент типа '"a"' не может быть присвоен параметру типа 'number'.



// [Предустановленные и опциональные параметры]
function log1(message: string, userId?: string) {
    let time = new Date().toLocaleTimeString()
    console.log(time, message, userId || 'Not signed in')
}
log1('Page loaded')                     // Логирует "12:38:31 PM // Page loaded Not signed in"
log1('User signed in', 'da763be') // Логирует "12:38:31 PM // User signed in da763be"

// TypeScript позволяет снабдить опциональные параметры значениями по умолчанию
// (параметр по умолчанию можно не ставить в конец списка в отличие от опционального)
function log2(message: string, userId = 'Not signed in') {
    let time = new Date().toISOString()
    console.log(time, message, userId)
}
log2('User clicked on a button', 'da763be')
log2('User signed out')

// (когда мы присваиваем userId значение по умолчанию, то удаляем его опциональную аннотацию ?. Больше нет необходимости его типизировать)
// вы также можете добавлять явные аннотации типов для параметров по умолчанию тем же способом, что и для обычных параметров:
type Context = {
    appId?: string
    userId?: string
}
function log3(message: string, context: Context = {}) {
    let time = new Date().toISOString()
    console.log(time, message, context.userId)
}

// [Оставшиеся параметры]
// Если функция получает список аргументов, можно передать этот список в виде массива:
function sum(numbers: number[]): number {
    return numbers.reduce((total, n) => total + n, 0)
}
sum([1, 2, 3]) // result 6


function sumVariadic(): number {
// Поскольку arguments является не настоящим массивом, а всего лишь его подобием, то сначала необходимо преобразовать его в массив и лишь затем вызвать для него встроенный метод .reduce:
    return Array
        .from(arguments)
        .reduce((total, n) => total + n, 0) // total и n - вывелись как any
}
sumVariadic(1, 2, 3) // вычисляется как 6


function sumVariadicSafe(...numbers: number[]): number { // разница с sum добавили ...
    return numbers.reduce((total, n) => total + n, 0)
}
sumVariadicSafe(1, 2, 3) // вычисляется как 6.


// Функция может иметь не более одного оставшегося параметра, и этот параметр должен быть последним в списке.
interface Console {
    log(message?: any, ...optionalParams: any[]): void
}

