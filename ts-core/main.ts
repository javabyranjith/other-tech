interface Person {
    name: string,
    age: number,
    aboutMe(): string
}

let john = {
    name: 'Jhon Victor',
    age: 22,
    aboutMe: function () {
        return 'My Name is: ' + this.name + ' and age is: ' + this.age;
    }

}

function intro(person: Person) {
    return person.aboutMe();
}

console.log(intro(john));
