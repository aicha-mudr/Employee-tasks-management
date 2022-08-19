/* tslint:disable */
import { UserDto } from './user-dto';
import { TodoDto } from './todo-dto';
export interface EmployeDto {
  id?: number;
  name?: string;
  function?: string;
  email?: string;
  password?: string;


  user?: UserDto;
  todoList?: Array<TodoDto>;
}
