/* tslint:disable */
import { EmployeDto } from './employe-dto';
export interface TodoDto {
  id?: number;
  title?: string;
  description?: string;
  startDate?: string;
  done?: boolean;
  favorite?: boolean;
  category?: EmployeDto;
}
