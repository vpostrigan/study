// [1] var - Хойстинг (всплытие объявления) в typescript
foo = 123;
console.log(foo);
var foo;
// компилятор сначала смотрит все 'var'

// тоже самое и с функциями (сначала можно вызвать а потом объявить)
bar();

function bar() {
    console.log('I am bar function');
}

// [2] let - объявили вверху и использовали внизу (only)
let test = 100;
console.log(test);

// let видна только в блоке из фигурных скобок
{
    let test = 'dddd';
    console.log(test) // будет 'dddd'
}
console.log(test); // будет 100

// [3] проблема асинхронного цикла
let array = [1, 2, 3]
for (var i = 0; i < array.length; i++) {
    setTimeout(function () {
        // i == undefined во всех случаях, так как вызвало 'array[3]' == undefined
        console.log(array[i]);
    }, 1000);
}

for (let i = 0; i < array.length; i++) {
    setTimeout(function () {
        // будет работать так как i будет создаваться для каждой итерации
        console.log(array[i]);
    }, 1000);
}

// другое решение использовать forEach
array.forEach(function (value: number) {
    setTimeout(function () {
        console.log('ForEach ' + value);
    }, 1000);
})

// [4] const
// сначала писать const, если нужно менять значение поменять на let
const VALUE = 500;
// VALUE = 100; - будет ошибка компиляции

// объекты
const OBJECT = {
    foo: 123,
    bar: 'test'
};
OBJECT.foo = 321;
OBJECT.bar = 'qwerty';
console.log(OBJECT);
// OBJECT = {}; - будет ошибка компиляции

// массивы
const names = ['John', 'Max'];
names.push('Ivan'); // можно добавить элемент
console.log(names);
