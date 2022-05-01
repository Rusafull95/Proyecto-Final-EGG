/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital;

import ProyectoFinal.example.Hospital.Servicios.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author AXEL
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Seguridad extends WebSecurityConfigurerAdapter{

    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(usuarioServicio).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/css/*","/img/*","/js/*").permitAll()
                .and().formLogin()
                      .loginPage("/login")
                      .usernameParameter("mail")
                      .passwordParameter("password")
                      .defaultSuccessUrl("/") // a donde va la pagina al lograr
                      .loginProcessingUrl("/logincheck")
                      .failureUrl("/login?error=error") // vuelve con un error
                      .permitAll()
                .and().logout()
                      .logoutUrl("/logout")
                      .logoutSuccessUrl("/login?logout")
                .and().csrf().disable();
    }
    
    //
            //    .logoutSuccessUrl("/login?logout"); para CSRFTOKEN ACTIVAR
            //    .and().csrf().disable();
    //
}
