import { User } from "@/models/user.model";
import axios from 'axios';

export async function createUser(user: User): Promise<User | null> {
  try {
    const response = await axios.post('', user.toPayload());
    console.log(response);
    return response.data;
  } catch (err) {
    console.log(err);
    return null;
  }
}
