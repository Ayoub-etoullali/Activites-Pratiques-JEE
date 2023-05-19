import {
  ActivatedRouteSnapshot,
  CanActivate, CanActivateFn,
  Router,
  RouterStateSnapshot,
  UrlTree
} from '@angular/router';
import {AuthenticationService} from "../services/authentication/authentication.service";
import {Observable} from "rxjs";

export const authenticationGuard: CanActivateFn = (route, state) => {
  return true;
};

export class AuthenticationGuard implements CanActivate {
  constructor(private authService: AuthenticationService, private router: Router) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let authenticated = this.authService.isAuthenticated();
    if (authenticated == false) {
      this.router.navigateByUrl("/login");
      return false;
    } else
      return true;
  }
}
