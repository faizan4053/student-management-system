import React from 'react';
import { useParams , useNavigate} from 'react-router-dom';

import StudentForm from '../components/student/StudentForm';

const EditStudentPage = () => {
  const { id } = useParams(); // Extract student ID from URL
  const navigate = useNavigate();
  const navigateToHome = (id) => {
    navigate(`/`); // Navigate to the edit page with the student ID
  };



  return (
    <div>
      <h1>Edit Student</h1>
      <button onClick={() => navigateToHome()}>Home</button>
      <StudentForm studentId={id} onSave={() => console.log('Student updated')} />
    </div>
  );
};

export default EditStudentPage;
