import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { map, mergeMap, tap } from 'rxjs';
import { SessionDto } from '../../dto/SessionDto';
import { environment } from '../../environments/environment';
import { UserDto } from '../../dto/UserDto';
import { UserComponent } from '../user/user.component';

@Component({
  standalone: true,
  imports: [UserComponent],
  templateUrl: './current-session.component.html'
})
export class CurrentSessionComponent implements OnInit {
  sessionId: string = '';
  users: UserDto[] = [];
  session: SessionDto | undefined;

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) { }

  ngOnInit() {
    this.route.params.pipe(
      map(routeParams => routeParams['sessionId']),
      tap(sessionId => this.sessionId = sessionId),
      mergeMap(sessionId => this.http.get<SessionDto>(`${environment.apiBaseURL}/session/${sessionId}`))
    ).subscribe(
      session => {
        this.sessionId = session.id;
        this.session = session;
        this.users = session.users;
      },
      _ => {
        console.log(`Session ${this.sessionId} doesn't exist`);
        this.router.navigate(['/']);
      }
    );
  }

}

