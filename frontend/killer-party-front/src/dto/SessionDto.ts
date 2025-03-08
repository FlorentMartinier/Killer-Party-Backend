import { UserDto } from "./UserDto";

export interface SessionDto {
    id: string,
    userCanJoin: boolean,
    users: UserDto[],
}