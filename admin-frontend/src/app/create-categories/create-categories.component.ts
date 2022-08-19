import { Component, OnInit } from '@angular/core';
import { EmployeDto } from 'src/todo-api/src/models/employe-dto';
import { UserDto } from 'src/todo-api/src/models/user-dto';
import { CategoryServiceService } from '../services/category-service.service';
import { Router, ActivatedRoute } from '@angular/router';
import { UserServiceService } from '../services/user-service.service';

@Component({
  selector: 'app-create-categories',
  templateUrl: './create-categories.component.html',
  styleUrls: ['./create-categories.component.scss']
})
export class CreateCategoriesComponent implements OnInit {

  public errors: string [];

  public employeDto: EmployeDto = {};
   public userDto: UserDto = {};
  constructor(
    public categoryService: CategoryServiceService,
    public router: Router,
    public activatedRoute: ActivatedRoute,
    public userService: UserServiceService
  ) { }

  ngOnInit(): void {
    this.resolveCategoryDto();
  }

  resolveCategoryDto() {
    const categoeyId = this.activatedRoute.snapshot.params.categoryId;
    if (categoeyId) {
      this.categoryService.getById(categoeyId)
      .subscribe(data => {
        this.employeDto = data;
      },
      error => {
        this.router.navigate(['categories']);
      });
    }
  }

  saveCategory(user : UserDto) {
    this.errors = [];
    this.employeDto.user = user;
    this.employeDto.admin = this.userService.getLoggedUser();
    console.log(this.employeDto.admin)//hna kanzid admin id
    this.categoryService.save(this.employeDto)
    .subscribe(data => {
      this.router.navigate(['categories']);
    },
    error => {
      this.errors = error.error.errors;
    });
  }

  cancel() {
    this.router.navigate(['categories']);
  }

    updateUser() {
      this.userService.saveUser(this.userDto)
      .subscribe(data => {
        this.userDto = data;


      },
      err => {
        this.errors = [];
        if (err.error.errors === null) {
          this.errors.push(err.error.message);
        } else {
          this.errors = err.error.errors;
        }
      });

    }
  updateUser2() {
    this.userService.saveUser(this.userDto)
      .subscribe(data => {
          this.userDto = data;


        },
        err => {
          this.errors = [];
          if (err.error.errors === null) {
            this.errors.push(err.error.message);
          } else {
            this.errors = err.error.errors;
          }
        });
    this.saveCategory(this.userDto);
  }
}
