var john = {
    name: 'Jhon Victor',
    age: 22,
    aboutMe: function () {
        return 'My Name is: ' + this.name + ' and age is: ' + this.age;
    }
};
function intro(person) {
    return person.aboutMe();
}
console.log(intro(john));
