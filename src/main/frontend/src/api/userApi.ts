// import {notifyError} from "../info/notifier";
import api from "./apiInstance";

// TODO: move to separate file
// Based on: com/example/splitapp/user/adapter/in/web/dto/UserDTO.java
export interface User {
  login: string
}

export const fetchUsersRequest = async (): Promise<User[] | undefined> => {
  try {
    const response = await api.get<User[]>("/users");
    return response.data;
  } catch (error) {
    // notifyError("Users not found"); // TODO: implement Toastify
    return undefined;
  }
};

// TODO: implement requests
// export const addNewUserRequest = async (user: Omit<User, 'id'>): Promise<User | undefined> => {
//     try {
//         const response = await api.post<User>(`/users`, user);
//         return response.data;
//     } catch (error) {
//         notifyError("Could not add the user");
//         return undefined;
//     }
// };
//
// export const deleteUserRequest = async (user: Pick<User, 'id'>): Promise<void> => {
//     try {
//         await api.delete(`/users/${user.id}`);
//     } catch (error) {
//         notifyError("Error while deleting the user");
//     }
// };
//
// export const updateUserRequest = async (user: User): Promise<User | undefined> => {
//     try {
//         const response = await api.put<User>(`/users/${user.id}`, user);
//         return response.data;
//     } catch (error) {
//         notifyError("Error while updating the user");
//         return undefined;
//     }
// };
