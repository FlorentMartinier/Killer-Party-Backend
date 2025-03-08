import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { combineLatest, mergeMap, tap } from 'rxjs';
import { IpResponse } from '../../dto/IpResponseDto';
import { UserDto } from '../../dto/UserDto';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-join-session',
  standalone: false,
  templateUrl: './join-session.component.html'
})
export class JoinSessionComponent implements OnInit {
  name: string = '';
  phoneNumber: string = '';
  sessionId: string = '';
  currentIpAdress: string = ''

  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    console.log('init')
    combineLatest([
      this.route.params,
      this.http.get<IpResponse>("https://api.ipify.org?format=json")
    ]).pipe(
      tap(([routeParams, ipObject]) => {
        this.sessionId = routeParams['sessionId']
        this.currentIpAdress = ipObject.ip
      }),
      mergeMap(_ => this.http.get<boolean>(`${environment.apiBaseURL}/user/${this.currentIpAdress}/session/${this.sessionId}`))
    )
    .subscribe(waitingGameExist => {
      if (waitingGameExist) {
        console.log('navigating', waitingGameExist)
        this.router.navigate(['/session', this.sessionId]);
      }
    });
  }

  onSubmit(): void {
    const formData: UserDto = {
      sessionId: this.sessionId,
      name: this.name,
      phoneNumber: this.phoneNumber,
      associatedIpAddress: this.currentIpAdress,
      state: 'WAITING',
    };

    this.http.post<UserDto>(`${environment.apiBaseURL}/user/create`, formData).subscribe(
      response => {
        console.log('Réponse du serveur :', response);
        this.router.navigate(['/session', response.sessionId]);
      },
      error => {
        console.error('Erreur lors de l\'appel HTTP :', error);
      }
    );
  }
}