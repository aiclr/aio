import { useState } from 'react';
// Object state
export function MyForm() {
  const [person, setPerson] = useState({
    firstName: 'Barbara',
    lastName: 'Hepworth',
    email: 'bhepworth@sculpture.com'
  });

  // 不能使用 let tmpPerson=person;
  // 复制 state 对象 写法1
  let tmpPerson = {
    firstName: person.firstName,
    lastName: person.lastName,
    email: person.email
  };

  // 复制 state 对象 写法2
  let tmpPerson2 = {
    ...person
  };

  function handleFirstNameChange(e) {
    tmpPerson.firstName = e.target.value;
    setPerson(tmpPerson);
  }

  function handleLastNameChange(e) {
    tmpPerson2.lastName = e.target.value;
    setPerson(tmpPerson2);
  }
  //写法三
  function handleEmailChange(e) {
    setPerson({
      ...person,
      email: e.target.value
    });
  }

  // 通过设置 组件name  实现一个方法处理多种事件
  function handleInput(event) {
    setPerson({
      ...person,
      [event.target.name]: event.target.value
    });
  }


  return (
    <>
      <label>
        First name:
        <input
          name='firstName'
          value={person.firstName}
          onChange={handleFirstNameChange}
        />
      </label>
      <label>
        Last name:
        <input
          name='lastName'
          value={person.lastName}
          onChange={handleLastNameChange}
        />
      </label>
      <label>
        Email:
        <input
          name='email'
          value={person.email}
          onChange={handleEmailChange}
        />
      </label>
      <hr />
      <label>
        First name:
        <input
          name='firstName'
          value={person.firstName}
          onChange={handleInput}
        />
      </label>
      <label>
        Last name:
        <input
          name='lastName'
          value={person.lastName}
          onChange={handleInput}
        />
      </label>
      <label>
        Email:
        <input
          name='email'
          value={person.email}
          onChange={handleInput}
        />
      </label>
      <p>
        {person.firstName}{' '}
        {person.lastName}{' '}
        ({person.email})
      </p>
    </>
  );
}
