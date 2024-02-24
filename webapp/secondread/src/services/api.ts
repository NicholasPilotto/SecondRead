import { User } from "@/models/user.model";
import axios from 'axios';

export async function createUser(user: User): Promise<User> {
  try {
    const response = await axios.post('', user.toPayload());
    console.log(response);
    return new User().deserialize(response);
  } catch (err) {
    console.log(err);
  }
}
