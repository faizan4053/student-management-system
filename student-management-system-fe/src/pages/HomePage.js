import React, { useState } from 'react';
import StudentList from '../components/student/StudentList';
import StudentForm from '../components/student/StudentForm';
import StudentSearch from '../components/student/StudentSearch';

const HomePage = () => {
  const [isFormVisible, setIsFormVisible] = useState(false);

  return (
    <div>
      <h1>Student Management Application</h1>
      <StudentSearch onSearch={(term) => console.log(`Searching for ${term}`)} />
      <StudentList />
      {isFormVisible && <StudentForm onSave={() => setIsFormVisible(false)} />}
      <button onClick={() => setIsFormVisible(!isFormVisible)}>
        {isFormVisible ? 'Close Form' : 'Add Student'}
      </button>
    </div>
  );
};

export default HomePage;
