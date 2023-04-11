export default null // Force module mode

// [any] выступает в роли крестного отца всех типов.
// any представляет собой набор всех возможных значений, и вы можете делать с ним все что
// угодно: прибавлять к нему, умножать на него...
let b0: any = ['danger']   // any
let c0 = b0 + 1             // any  // нет сообщения об ошибке
// Чтобы активировать функцию защиты от неявных any, нужно добавить флаг noImplicitAny в tsconfig.json.
// noImplicitAny становится активна при включении режима strict в tsconfig.json


// [unknown]   представляет любое значение, но чтобы использовать это значение,
//           TypeScript потребует уточнить его тип
// Какие же операции поддерживает unknown?
// Вы можете сравнивать значения unknown (==, ===, ||, && и ?), отрицать их (!) и уточнять (как и любой
// другой тип через JavaScript-операторы typeof и instanceof). Применяется же он следующим образом:
let a1: unknown = 30         // unknown
let b1 = a1 === 123  // boolean
let c1 = a1 + 10             // Ошибка TS2571: объект имеет тип 'unknown'.
if (typeof a1 === 'number') {
    let d = a1 + 10  // number, так ошибки нет
}
// общее представление об использовании unknown.
// 1. TypeScript никогда не выводит unknown — этот тип нужно явно аннотировать (a1).
// 2. Можно сравнивать значения со значениями типа unknown (b1).
// 3. Нельзя производить действия на основе предположения, что значение
// unknown имеет конкретный тип (c1). Сначала нужно показать TypeScript наличие этого типа (d)


// [boolean] Такие типы можно сравнивать (==, ===, ||, && и ?) и отрицать (!). Используются они так:
let a3 = true   // boolean
var b3 = false  // boolean
const c3 = true    // true    (TypeScript вывел тип c3 как true вместо boolean)
let d3: boolean = true   // boolean
let e3: true = true      // true    (создал литералы типов) [ЛИТЕРАЛ ТИПА - Тип, представляющий только одно значение и ничто другое.]
let f3: true = false     // Ошибка TS2322: тип 'false' не может быть присвоен типу 'true'.

// сообщить TypeScript, что некий элемент имеет тип boolean, можно сделать следующее:
// 1. Позволить TypeScript вывести тип boolean для значения (a3 и b3).
// 2. Позволить TypeScript вывести конкретное значение boolean (c).
// 3. Явно сообщить TypeScript, что значение является boolean (d). [практически никогда]
// 4. Явно сообщить TypeScript, что значение имеет конкретное значение boolean (e и f). [очень редко]


// [number] представляет набор чисел: целочисленные, с плавающей запятой, положительные, отрицательные, Infinity (бесконечность), NaN и т.д.
// Для чисел доступно достаточно много действий: сложение (+), вычитание (-), деление по модулю (%) и сравнение (<). Примеры:
let a4 = 1234             // number
var b4 = Infinity * 0.10  // number
const c4 = 5678             // 5678
let d4 = a4 < b4          // boolean
let e4: number = 100              // number
let f4: 26.218 = 26.218           // 26.218
let g4: 26.218 = 10               // Ошибка TS2322: тип '10' не может быть присвоен типу '26.218'.

let oneMillion = 1_000_000 // Эквивалент 1000000
let twoMillion: 2_000_000 = 2_000_000

// Для типизации с помощь number можно сделать следующее:
// 1. Позволить TypeScript вывести тип значения как number (a4 и b4).
// 2. Использовать const, чтобы TypeScript вывел тип переменной как конкретное число (number) (c4).
// 3. Явно сообщить TypeScript, что значение имеет тип number (e4). [практически никогда]
// 4. Явно сообщить, что значение имеет конкретный тип number (f4 и g4). [иногда]


// [bigint] number может представлять целые числа только до 2^53
// поддерживает такие действия, как сложение (+), вычитание (-), умножение (*), деление (/) и сравнение (<). Примеры:
let a5 = 1234n       // bigint
const b5 = 5678n     // 5678n
var c5 = a5 + b5     // bigint
let d5 = a5 < 1235 // boolean
let e5 = 88.5n            // Ошибка TS1353: литерал bigint должен быть целым числом.
let f5: bigint = 100n       // bigint
let g5: 100n = 100n         // 100n
let h5: bigint = 100     // Ошибка TS2322: тип '100' не может быть присвоен типу 'bigint'.

// Позволить TypeScript вывести тип значения (лучший вариант)


// [string] Тип string является набором всех строк и доступных для них операций
// вроде конкатенации (+), среза (.slice) и т. д. Вот несколько примеров: Типы от а до я 41
let a6 = 'hello'            // string
var b6 = 'billy'            // string
const c6 = '!'                // '!'
let d6 = a6 + ' ' + b6 + c6 // string
let e6: string = 'zoom'            // string let f: 'john' = 'john' // 'john'
let g6: 'john' = 'zoe'            // Ошибка TS2322: тип "zoe" не может быть присвоен типу "john".

// Позволить TypeScript вывести тип значения (лучший вариант)


// [symbol] появился с одной из последних ревизий JavaScript (ES2015).
// На практике символы встречаются нечасто.
let a7 = Symbol('a') // symbol
let b7: symbol = Symbol('b') // symbol
var c7 = a7 === b7            // boolean
let d7 = a7 + 'x'              // Ошибка TS2469: оператор '+' не может быть применен к типу 'symbol'.

// Задача Symbol('a') в JavaScript заключается в создании нового symbol с заданным именем.
// Этот symbol уникален и не будет равен (при сравнении посредством == или ===)
// никакому другому symbol (даже если создать второй одноименный symbol).
const e7 = Symbol('e')                // typeof e (Когда вы объявляете новый symbol и присваиваете его переменной const (не var или let), TypeScript выводит ее тип как unique symbol)
const f7: unique symbol = Symbol('f') // typeof f
let g7: unique symbol = Symbol('f')   // Ошибка TS1332: переменная, имеющая тип 'unique symbol', должна быть 'const'.
let h7 = e7 === e7   // boolean
let i7 = e7 === f7 // Ошибка TS2367: это условие всегда будет возвращать 'false', поскольку типы 'unique symbol' и 'unique symbol' не имеют сходства.


// [object] определяет форму объекта.
// несколько способов использования типов для описания объектов:
// 1) объявление значения в качестве object:
let a8: object = {
    b8: 'x'
}
a8.b8 // Ошибка TS2339: свойство 'b' не существует в типе 'object'.

const a81: {b81: number} = {
    b81: 12
}
a81.b81 // По-прежнему {b: number} так как объект, объявленный с const, не подскажет TypeScript
// вывести более узкий тип. Дело в том, что JavaScript-объекты изменяемы
// и TypeScript знает, что их поля можно обновить после создания.

// 2) синтаксис объектного литерала.
// если не использовать явные аннотации и позволим TypeScript делать свою работу?
let a82= {
    b82: 'x'
}                        // {b: string}
a82.b82                  // string

let b82= {
    c82: {
        d82: 'f'
    }
} // {c82: {d82: string}}

// объектным литералом
let c82: {
    firstName: string
    lastName: string
} =
    {
        firstName: 'john',
        lastName: 'barrowman'
    }
// либо классом
class Person {
    constructor(public firstName: string, public lastName: string) {
        // public является сокращением this.firstName = firstName
    }
}
c82 = new Person('matt', 'smith')    // OK // TypeScript позволяет присвоить Person литералу c82.

// что произойдет, если добавить дополнительные свойства или упустить необходимые:
let a83: {b83: number}
a83 = {}  // Ошибка TS2741: свойство 'b' отсутствует в типе '{}', но необходимо в типе '{b: number}'.

a83 = {
    b83: 1,
    c83: 2
    // Ошибка TS2322: тип '{b: number; c: number}' не может быть присвоен типу '{b: number}'.
    // Объектный литерал может определять только известные свойства, а 'c' не существует в типе '{b: number}'.
}


let a84: {
    b: number
    c?: string // a84 может иметь свойство c, являющееся string. Если с задано, то оно может быть undefined.
    [key: number]: boolean // a84 может иметь любое количество численных свойств, являющихся boolean.
}

a84 = {b: 1}
a84 = {b: 1, c: undefined}
a84 = {b: 1, c: 'd'}
a84 = {b: 1, 10: true}
a84 = {b: 1, 10: true, 20: false}
a84 = {10: true} // Ошибка TS2741: свойство 'b' упущено в типе'{10: true}'.
a84 = {b: 1, 33: 'red'} // Ошибка TS2741: тип 'string' не может быть присвоен типу 'boolean'.


// При объявлении типа object можно использовать как модификатор
// опциональности (?), так и модификатор readonly, который не позволит
// изменять поле после присвоения ему первого значения (своего рода const для свойств объекта):
let user: {
    readonly firstName: string
} = {
    firstName: 'abby'
}
user.firstName // string
user.firstName = 'abbey with an e' // Ошибка TS2540: невозможно присвоить к 'firstName', т.к. это свойство только для чтения.


// Каждый тип, за исключением null и undefined, может быть присвоен типу
// пустого объекта ({}), что усложняет его использование. Старайтесь избегать типов пустых объектов:
let danger: {}
danger = {}
danger = {x: 1}
danger = []
danger = 2

// Например, следующий код проходит проверку типов:
let a85: {} = {
    toString(){
        return 3
    }}
// Но, если вы измените аннотацию типа на Object, то TypeScript укажет ошибку:
let b85: Object = {
    toString(){
        return 3
    }}
// «Тип 'number' не может быть присвоен типу 'string'»


// СИГНАТУРЫ ИНДЕКСОВ
// Синтаксис [key: T]: U называется сигнатурой индекса.
// Читать так: «Для этого объекта все ключи типа T должны иметь значения типа U».
// Сигнатуры индекса позволяют безопасно добавлять дополнительные ключи объекту, помимо объявленных ранее.
// Но тип (T) ключа сигнатуры индекса должен быть совместим либо со string, либо с number1.
// В качестве имени ключа сигнатуры индекса можно использовать любое слово — не только key:
let airplaneSeatingAssignments: {
    [seatNumber: string]: string
} = {
    '34D': 'Boris Cherny',
    '34E': 'Bill Gates'
}

// В общей сложности выходит четыре способа объявления объектов в TypeScript:
// 1. Объявление объектного литерала (вроде {a: string}), называемого также формой.
// Используйте ее, когда знаете, какие поля будет иметь объект, или когда все его значения будут иметь один тип.
// 2. Объявление пустого объектного литерала ({}). Старайтесь его избегать.
// 3. Тип object. Используйте его, когда вам просто нужен объект и неважно, какие у него поля.
// 4. Тип Object. Старайтесь его избегать.

// Является ли значение допустимым объектом
// Value            {}          object         Object
// {}               Да            Да             Да
// ['a']            Да            Да             Да
// function () {}   Да            Да             Да
// new String('a')  Да            Да             Да
// new String('a')  Да            Да             Да
// 'a'              Да            Нет            Да
// 1                Да            Нет            Да
// Symbol('a')      Да            Нет            Да
// null             Нет           Нет            Нет
// undefined        Нет           Нет            Нет





// 1. For each of these values, what type will TypeScript infer?

// 1a
let a = 1042 // number

// 1b
let b = 'apples and oranges' // string

// 1c
const c = 'pineapples' // 'pineapples'

// 1d
let d = [true, true, false] // boolean[]

// 1e
let e = {type: 'ficus'} // {type: string}

// 1f
let f = [1, false] // (number | boolean)[]

// 1g
const g = [3] // number[]

// 1h
let h = null // any

// 2. Why does each of these throw the error it does?

// 2a
let i: 3 = 3
i = 4 // Error TS2322: Type '4' is not assignable to type '3'.

/*
i's type is the type literal 3. The type of 4 is the type literal 4, which is not assignable to the type literal 3.
*/

// 2b
let j = [1, 2, 3]
j.push(4)
j.push('5') // Error TS2345: Argument of type '"5"' is not
// assignable to parameter of type 'number'.

/*
Since j was initialized with a set of numbers, TypeScript inferred j's type as number[].
The type of '5' is the type literal '5', which is not assignable to number.
*/

// 2c
let k: never = 4 // Error TS2322: Type '4' is not assignable to type 'never'.

/*
never is the bottom type. That means it's assignable to every other type, but no type is
assignable to never.
*/

// 2d
let l: unknown = 4
let m = l * 2 // Error TS2571: Object is of type 'unknown'.

/*
unknown represent a value that could be anything at runtime. To prove to TypeScript that what
you're doing is safe, you have to first prove to TypeScript that a value of type unknown actually
has a more specific subtype. You do that by refining the value using typeof, instanceof, or
another type query or type guard.
*/
