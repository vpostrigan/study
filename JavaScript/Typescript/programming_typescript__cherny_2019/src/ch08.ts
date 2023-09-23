// Асинхронное программирование, конкурентность и параллельная обработка

// [Цикл событий]
import {readFile} from "fs";

setTimeout(() => console.info('A'), 1)
setTimeout(() => console.info('B'), 2)
console.info('C')
// значения появятся в порядке C, A, B


// [Работа с обратными вызовами]
// Для самостоятельного запуска следующего примера установите декларации типов для NodeJS:
// npm install @types/node --save-dev

// NodeJS-программу, производящую считывание и запись в журнал доступа Apache:
import * as fs from 'fs'
// Считывание данных из журнала доступа сервера Apache.
fs.readFile(
    '/var/log/apache2/access_log',
    {encoding: 'utf8'},
    (error, data) => {
        if (error) {
            console.error('error reading!', error)
            return
        }
        console.info('success reading!', data)
    }
)

// Параллельное записывание данных в тот же журнал доступа.
fs.appendFile(
    '/var/log/apache2/access_log',
    'New access log entry',
    error => {
        if (error) {
            console.error('error writing!', error)
        }
    })

// функция асинхронная, если ее последний аргумент — это функция,
// получающая два аргумента — Error | null и T | null, именно в таком порядке.


// Мало того что типы не помогают постичь синхронность функций,
// обратные вызовы тоже сложно упорядочиваемы и могут выстраиваться в так называемые пирамиды обратных вызовов:
async1((err1, res1) => {
    if (res1) {
        async2(res1, (err2, res2) => {
            if (res2) {
                async3(res2, (err3, res3) => {
                    // ...
                })
            }
        })
    }
})


// [Промисы как здоровая альтернатива]
// пример использования промисов для добавления данных в файл и последующего считывания результата:
function appendAndReadPromise(path: string, data: string): Promise<string> {
    return appendPromise(path, data)
        .then(() => readPromise(path))
        .catch(error => console.error(error))
}

// аналог на обратных вызовах
function appendAndRead(path: string, data: string,
                       cb: (error: Error | null, result: string | null) => void) {
    appendFile(path, data, error => {
        if (error) {
            return cb(error, null)
        }
        readFile(path, (error, result) => {
            if (error) {
                return cb(error, null)
            }
            cb(null, result)
        })
    })
}

// Создадим API Promise.
type Executor<T> = (
    resolve: (result: T) => void,
    reject: (error: unknown) => void
) => void

class Promise<T> {
    constructor(f: Executor<T>) {}
    then<U>(g: (result: T) => Promise<U>): Promise<U> {
        // ...
    }
    catch<U>(g: (error: unknown) => Promise<U>): Promise<U> {
        // ...
    }
}

function readFilePromise(path: string): Promise<string> {
    return new Promise((resolve, reject) => {
        readFile(path, (error, result) => {
            if (error) {
                reject(error)
            } else {
                resolve(result)
            }
        })
    })
}

// Использование then:
// let a: ()          => Promise<string, TypeError> = // ...
// let b: (s: string) => Promise<number, never> = // ...
// let c: ()          => Promise<boolean, RangeError> = // ...

// a()
//         .then(b)
//         .catch(e => c()) // b не будет ошибкой, так что это случай ошибки a
//         .then(result => console.info('Done', result))
//         .catch(e => console.error('Error', e))

// a() -> b() -> 'Done'
// a() -> b() -> c() -> 'Done' | 'Error'
// a() -> c() -> 'Done' | 'Error'


// [async и await]
// Рассматривайте await как синтаксический сахар для .then на уровне языка.
// Когда вы ожидаете (await) Promise, то должны делать это в блоке async.
// И вместо .catch вы можете обернуть await в регулярный блок try... catch.
// пример:
function getUser1() {
    getUserID(18)
        .then(user => getLocation(user))
        .then(location => console.info('got location', location))
        .catch(error => console.error(error))
        .finally(() => console.info('done getting location'))
}

// преобразовать этот код в async и await
async function getUser2() {
    try {
        let user = await getUserID(18)
        let location = await getLocation(user)
        console.info('got location', user)
    } catch(error) {
        console.error(error)
    } finally {
        console.info('done getting location')
    }
}



// [Async-потоки]

// Отправители событий
