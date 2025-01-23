import axios from 'axios';

const API_URL = 'http://localhost:8081/api/v1/students';

export const getStudents = () => axios.get(API_URL);
export const getStudentById = (id) => axios.get(`${API_URL}/${id}`);
export const getStudentByName = (name) => axios.get(`${API_URL}/${name}`);
export const addStudent = (student) => axios.post(API_URL, student);
export const updateStudent = (id, student) => axios.put(`${API_URL}/${id}`, student);
export const deleteStudent = (name) => {
    console.log('inside delete ', name)
    axios.delete(`${API_URL}/${name}`);
}
