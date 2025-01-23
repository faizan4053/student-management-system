import React, { useState, useEffect } from 'react';
import { addStudent, updateStudent, getStudentById } from '../../api/ApiService';

const StudentForm = ({ studentId, onSave }) => {
  const [student, setStudent] = useState({
    name: '',
    age: '',
    studentClass: '',
    phoneNumber: '',
  });

  useEffect(() => {
    if (studentId) {
      fetchStudent(studentId);
    }
  }, [studentId]);

  const fetchStudent = async (id) => {
    try {
      const response = await getStudentById(id);
      setStudent(response.data);
    } catch (error) {
      console.error('Error fetching student:', error);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setStudent({ ...student, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (studentId) {
        await updateStudent(studentId, student);
      } else {
        await addStudent(student);
      }
      onSave();
    } catch (error) {
      console.error('Error saving student:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Name:</label>
        <input
          type="text"
          name="name"
          value={student.name}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label>Age:</label>
        <input
          type="text"
          name="age"
          value={student.age}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label>Class:</label>
        <input
          type="text"
          name="studentClass"
          value={student.studentClass}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label>Phone Number:</label>
        <input
          type="number"
          name="phoneNumber"
          value={student.phoneNumber}
          onChange={handleChange}
          required
        />
      </div>
      <button type="submit">Save</button>
    </form>
  );
};

export default StudentForm;
