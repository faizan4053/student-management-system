/* eslint-disable no-undef */
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { getStudents, deleteStudent } from '../../api/ApiService';

const StudentList = () => {
  const [students, setStudents] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetchStudents();
  }, []);

  const fetchStudents = async () => {
    try {
      const response = await getStudents();
      console.log(response);
      setStudents(response.data);
    } catch (error) {
      console.error('Error fetching students:', error);
    }
  };

  const handleDelete = async (name) => {
    try {
      await deleteStudent(name);
      setStudents(students.filter((student) => student.name !== name));
    } catch (error) {
      console.error('Error deleting student:', error);
    }
  };

  const handleEdit = (id) => {
    navigate(`/edit/${id}`); // Navigate to the edit page with the student ID
  };


  return (
    <div>
      <h2>Student List</h2>
      <ul>
        {students.map((student) => (
          <li key={student.id}>
            {student.name} ({student.studentClass}) - {student.phoneNumber}
            <button onClick={() => handleEdit(student.id)}>Edit</button> {/* Edit Button */}
            <button onClick={() => handleDelete(student.name)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default StudentList;
