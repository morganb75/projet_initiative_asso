import {jwtDecode} from "jwt-decode";

const decodeToken = (token) => {
   try{
       const decoded = jwtDecode(token)
       return decoded
   } catch (error) {
       console.error('invalid token: ', error)
       return null
   }
};

export default decodeToken