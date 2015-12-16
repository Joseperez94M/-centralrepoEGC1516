﻿using Authentication.Models;
using Authentication.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;

namespace Authentication.Controllers
{
    /// <summary>
    /// Este es el controlador principal de la aplicación, es el que se encarga de orquestar las peticiones http a la home del nuestro subsistema.
    /// Tiene los métodos: login, para logear y registrar usuarios.
    /// 
    /// </summary>
    public class HomeController : Controller
    {
        UserRepository ur = new UserRepository();

        /// <summary>
        /// Función de logeo que devuelve la vista de logeo.
        /// </summary>
        /// <param name="returnUrl"></param>
        /// <returns></returns>
        public ActionResult Login(string returnUrl)
        {
            ViewBag.ReturnUrl = returnUrl;
            return View();
        }
        /// <summary>
        /// Función de logeo post en el controlador.
        /// </summary>
        /// <param name="model"></param>
        /// <param name="returnUrl"></param>
        /// <returns></returns>
        [ValidateAntiForgeryToken]
        [HttpPost]
        public ActionResult Login(UserLoginModel model, string returnUrl)
        {
            if (!ModelState.IsValid)
            {
                return View(model);
            }

            if (ur.ValidLogin(model.username, UserRepository.GetSHA512(model.password, model.username)))
            {
                ModelState.AddModelError("", "Login correcto."); //Para testeo

                HttpCookie cookieUser = new HttpCookie("user", model.username);
                HttpCookie cookieToken = new HttpCookie("token", model.username + ":" + UserRepository.GetSHA512(model.username + UserRepository.GetSHA512(model.password, model.username), model.username));
                cookieUser.Expires = DateTimeOffset.Now.AddYears(1).Date;
                cookieToken.Expires = DateTimeOffset.Now.AddYears(1).Date;
                this.HttpContext.Response.SetCookie(cookieUser);
                this.HttpContext.Response.SetCookie(cookieToken);

                if (String.IsNullOrEmpty(returnUrl))
                    return View(model);
                else
                    return Redirect(returnUrl);
            }
            else
            {
                ModelState.AddModelError("", "Intento de inicio de sesión no válido.");
                return View(model);
            }
        }
        /// <summary>
        /// Función de registro post en el controlador.
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        [ValidateAntiForgeryToken]
        [HttpPost]
        public ActionResult Register(UserRegisterModel model)
        {
            if (!ModelState.IsValid)
            {
                return View(model);
            }
            else
            {
                ModelState.AddModelError("", "Registro correcto.");
                User user = new Models.User();

                user.age = (int)model.age;
                user.autonomous_community = model.autonomous_community;
                user.email = model.email;
                user.genre = model.genre;
                user.password = UserRepository.GetSHA512(model.password, model.username);
                user.username = model.username;

                ur.Add(user);
                return RedirectToAction("login", "home");
            }
        }
        /// <summary>
        ///  Función get de la vista de registro
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        public ActionResult Register()
        {
            UserRegisterModel model = new UserRegisterModel();


            return View(model);


        }
    }
}
