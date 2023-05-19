import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';


platformBrowserDynamic().bootstrapModule(AppModule) //démmarer un module s'appelle <AppModule>
  .catch(err => console.error(err));
