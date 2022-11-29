import { useState } from 'react';

// 抽出 类似的状态
export function Form() {
  const [firstName, setFirstName] = useState('Mary');
  const [lastName, setLastName] = useState('Poppins');

  function handleFirstNameChange(e) {
    setFirstName(e.target.value);
  }

  function handleLastNameChange(e) {
    setLastName(e.target.value);
  }

  return (
    <>
      <label>
        First name:
        <input value={firstName} onChange={handleFirstNameChange} />
      </label>
      <label>
        Last name:
        <input value={lastName} onChange={handleLastNameChange} />
      </label>
      <p><b>Good morning, {firstName} {lastName}.</b></p>
    </>
  );
}


function useFormInput(initValue){
    const [value, setValue] = useState(initValue);

    function handleInput(e){
        setValue(e.target.value)
    }

    const optional={
        value: value,
        onChange: handleInput
    }

    return optional;

}

export function FormPlus() {
    const firstName=useFormInput('Mary')
    const lastName=useFormInput('Poppins')
  
    return (
      <>
        <label>
          First name:
          <input {...firstName} />
        </label>
        <label>
          Last name:
          <input  {...firstName} />
        </label>
        <p><b>Good morning, {firstName.value} {lastName.value}.</b></p>
      </>
    );
  }