// Продвинутые типы

// [Связи между типами]

// Подтипы и супертипы
// Массив является подтипом объекта (Везде, где нужен объект, можно использовать массив.)
// Кортеж является подтипом массива (Везде, где нужен массив, можно использовать кортеж.)
// Все является подтипом any (Везде, где нужен any, можно использовать объект.)
// never является подтипом всего (Везде можно использовать never)
// Класс Bird, расширяющий класс Animal, — это подтип класса Animal (Везде, где нужен Animal, можно использовать Bird.)

// Вариантность

// Вариантность формы и массива

// Существующий пользователь, переданный с сервера.
type ExistingUser = {
    id: number
    name: string
}
// Новый пользователь, еще не сохраненный на сервере.
type NewUser = {
    name: string
}
function deleteUser(user: {id?: number, name: string}) {
    delete user.id
}

let existingUser: ExistingUser = {
    id: 123456,
    name: 'Ima User'
}
deleteUser(existingUser)

// противоположный случай (можно ли присвоить объект там, где ожидается его подтип?)
type LegacyUser = {
    id?: number | string
    name: string
}
let legacyUser: LegacyUser = {
    id: '793331',
    name: 'Xin Yang'
}
// Ошибка TS2345: aргумент типа 'LegacyUser' несовместим с параметром типа '{id?: number |undefined, name: string}'.
// Тип 'string' несовместим с типом 'number | undefined'.
deleteUser(legacyUser)


// Вариантность функции

// Crow <: Bird <: Animal
class Animal {
}
class Bird extends Animal {
    chirp() {}
}
class Crow extends Bird {
    caw() {}
}

function chirp(bird: Bird): Bird {
    bird.chirp()
    return bird
}

chirp(new Animal) // Ошибка TS2345: аргумент типа 'Animal' несовместим с параметром типа 'Bird'.
chirp(new Bird)
chirp(new Crow)

// новая функция. На этот раз ее параметр будет функцией:
function clone(f: (b: Bird) => Bird): void {
    let parent = new Bird
    let babyBird = f(parent)
    babyBird.chirp()
}
// безопасные функции для передачи
function birdToBird(b: Bird): Bird {
    return new Bird
}
clone(birdToBird) // OK

function birdToCrow(d: Bird): Crow {
    return new Crow
}
clone(birdToCrow) // OK

//
function birdToAnimal(d: Bird): Animal {
    return new Animal
}
clone(birdToAnimal) // Ошибка TS2345: аргумент типа '(d: Bird) => Animal' несовместим с параметром типа
                    // '(b: Bird) => Bird'.Тип 'Animal' несовместим с типом 'Bird'.

// а что насчет типов параметров?
function animalToBird(a: Animal): Bird {
    // ...
}
clone(animalToBird) // OK

function crowToBird(c: Crow): Bird {
    // ...
}
clone(crowToBird) // Ошибка TS2345: аргумент типа '(c: Crow) => Bird' несовместим с параметром типа ' (b: Bird) => Bird'.


// [Совместимость]

// [Расширение типов]
// Когда объявляете переменную как измененяемую (с let или var), ее тип расширяется от типа значения ее литерала до базового типа
let a600 = 'x'           // string
let b600 = 3           // number
var c600 = true        // boolean
const d600 = {x: 3} // {x: number}
enum E600 {X, Y, Z}
let e600 = E600.X

// Это не касается неизменяемых деклараций:
const a601 = 'x'           // 'x'
const b601 = 3              // 3
const c601 = true         // true
enum E601 {X, Y, Z}
const e601 = E601.X     // E.X

// Можно использовать явную аннотацию типа, чтобы не допустить его расширения
let a602: 'x' = 'x'             // 'x'
let b602: 3 = 3                 // 3
var c602: true = true           // true
const d602: {x: 3} = {x: 3}     // {x: 3}

// Это не касается неизменяемых деклараций:
const a603 = 'x'           // 'x'
const b603 = 3              // 3
const c603 = true         // true
enum E603 {X, Y, Z}
const e = E603.X        // E.X

// Можно использовать явную аннотацию типа, чтобы не допустить его расширения:
let a604: 'x' = 'x'          // 'x'
let b604: 3 = 3              // 3
var c604: true = true        // true
const d604: {x: 3} = {x: 3}  // {x: 3}

// Когда повторно присваиваете нерасширенный тип с помощью let или var, TypeScript расширяет его за вас.
const a605 = 'x'   // 'x'
let b605 = a605  // string
// Чтобы это предотвратить, добавьте явную аннотацию типа в оригинальную декларацию:
const c605: 'x' = 'x'   // 'x'
let d605 = c605    // 'x'

// Переменные, инициализированные как null или undefined, расширяются до any:
let a606 = null    // any
a606 = 3           // any
a606 = 'b'         // any

// Но, когда переменная, инициализированная как null или undefined,
// покидает область, в которой была объявлена, TypeScript присваивает ей определенный тип:
function x607() {
    let a = null   // any
    a = 3          // any
    a = 'b'        // any
    return a
}
x607()             // string

// [Тип const] (Используйте as const, когда хотите, чтобы TypeScript вывел максимально узкий тип.)
let a608= {x: 3} // {x: number}
let b608: {x: 3} // {x: 3}
let c608= {x: 3} as const // {readonly x: 3}

// const исключает расширение типа и рекурсивно отмечает его члены как readonly даже в глубоко вложенных структурах данных:
let d608 = [1, {x: 2}] // (number | {x: number})[]
let e608 = [1, {x: 2}] as const // readonly [1, {readonly x: 2}]


// [Проверка лишних свойств]
type Options = {
    baseURL: string
    cacheSize?: number
    tier?: 'prod' | 'dev'
}
class API {
    constructor(private options: Options) {}
}

new API({
    baseURL: 'https://api.mysite.com',
    tier: 'prod'
})

new API({
    baseURL: 'https://api.mysite.com',
    tierr: 'prod' // Ошибка TS2345: аргумент типа '{tierr: string}' несовместим с параметром типа 'Options'.
                  // Объектный литерал может определять только известные свойства,
                  // но 'tierr' не существует в типе 'Options'. Вы хотели написать 'tier'?
})

new API({
    baseURL: 'https://api.mysite.com',
    badTier: 'prod'
} as Options)     // Делаем утверждение, что неверный объект опций имеет тип Options. (ошибок нет)

// TypeScript больше не воспринимает его как новый и, произведя проверку лишних свойств,
// делает заключение, что ошибок нет.
let badOptions = {
    baseURL: 'https://api.mysite.com',
    badTier: 'prod'
}
new API(badOptions)

// Когда явно типизируем options как Options, объект, присваиваемый нами options, является новым,
// поэтому TypeScript выполняет проверку лишних свойств и находит баг.
    let options: Options = {
    baseURL: 'https://api.mysite.com',
    badTier: 'prod' // Ошибка TS2322: тип '{baseURL: string; badTier: string}' несовместим с типом 'Options'.
}
new API(options)


// [Уточнение]

// Мы используем объединение строчных литералов для описания
// возможных значений, которые может иметь единица измерения CSS
type Unit = 'cm' | 'px' | '%'
// Перечисление единиц измерения
let units: Unit[] = ['cm', 'px', '%']

function parseUnit(value: string): Unit | null {
    for (let i = 0; i < units.length; i++) {
        if (value.endsWith(units[i])) {
            return units[i]
        }
    }
    return null
}

type Width = {
    unit: Unit,
    value: number
}

function parseWidth(width: number | string | null | undefined): Width | null {
    if (width == null) {
        // true и для null, и для undefined
        return null
    }
    // с этого момента тип width — это number | string (он больше не может быть null или undefined).
    // Мы говорим, что тип был уточнен из number | string | null | undefined в number | string.

    // Если width — number, предустановить пикселы.
    if (typeof width == 'number') {
        // теперь TypeScript знает, что width — это number
        return {unit: 'px', value: width}
    }
    let unit = parseUnit(width)
    if (unit) { // В JavaScript есть семь обозначений понятия «неверно»: null, undefined, NaN, 0, -0, '' '' и false. Все остальные относятся к понятию «верно».
        return {unit, value: parseFloat(width)}
    }
    return null
}
