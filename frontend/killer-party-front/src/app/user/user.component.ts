import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { IpResponse } from '../../dto/IpResponseDto';
import { UserDto } from '../../dto/UserDto';

@Component({
  selector: 'app-user',
  standalone: true,
  templateUrl: './user.component.html',
})
export class UserComponent implements OnInit {
  @Input() user?: UserDto | undefined;
  currentIpAdress?: string | undefined;

  ipIsCurrentUser: boolean = false;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<IpResponse>("https://api.ipify.org?format=json").subscribe(ipObject => {
      this.currentIpAdress = ipObject.ip
      this.ipIsCurrentUser = this.currentIpAdress === this.user?.associatedIpAddress
    });
  }
}
