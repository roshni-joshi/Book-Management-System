import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BasicAuthenticationService } from '../basic-authentication.service';
import { BookRestService } from '../service/book-rest.service';

export class Book {

  constructor(
    public id: number,
    public name: string,
    public authorName: string,
    public publishDate: Date,
    public price: number
  ) {}
}

@Component({
  selector: 'app-list-books',
  templateUrl: './list-books.component.html',
  styleUrls: ['./list-books.component.css']
})
export class ListBooksComponent implements OnInit {

  books: Book[] = []
  deleteMessage = ''
  username = ''

  // books = [
  //   new Book(1, 'Python Programmming', 'Nagarajan Rao', new Date(), 700.00),
  //   new Book(2, 'Java Programmming', 'Amish Patel', new Date(), 500.87),
  //   new Book(3, 'C Programmming', 'Balagurusamy', new Date(), 450.50)
  // ]

  // book = {
  //   id : 1,
  //   name : 'Python Programmming',
  //   authorName : 'Nagarajan Rao',
  //   publishDate : new Date(),
  //   price : 700.00
  // }

  constructor(
    private bookRestService: BookRestService,
    private router: Router,
    private basicAuthenticationService: BasicAuthenticationService
  ) { }

  ngOnInit(): void {
    let user = this.basicAuthenticationService.getAuthenticatedUser();
    if(user) {
      this.username = user
    }
    this.refreshBooks();
  }

  refreshBooks() {
    if(this.username) {
      this.bookRestService.retrieveAllBooks(this.username).subscribe(
        response => this.books = response
      );
    }
  }

  deleteBook(id: number) {
    if(this.username) {
      this.bookRestService.deleteBook(this.username, id).subscribe(
        response => {
          this.deleteMessage = `Deletion of book ${id} successful!`;
          this.refreshBooks();
        }
      )
    }
  }

  updateBook(id: number) {
    this.router.navigate(['bookform', id]);
  }

  addBook() {
    this.router.navigate(['bookform', -1]);
  }
}
