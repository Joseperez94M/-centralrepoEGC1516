using Authentication.Models;
using Authentication.Repositories;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace Authentication.Infraestructure
{
    /// <summary>
    /// En esta clase, se generan los datos de prueba de nuestra api.
    /// </summary>
    public class MyDbInitializer : DropCreateDatabaseAlways<AuthContext>
    {
        protected override void Seed(AuthContext context)
        {

            // hash md5(test) = f6f4061a1bddc1c04d8109b39f581270
            context.Users.Add(new User
            { username = "test0", password = UserRepository.GetSHA512("test0", "test0"), email = "jorgeron1993@hotmail.com", genre = "Masculino", autonomous_community = "Andalucia", age = 21 });
            context.Users.Add(new User
            { username = "test1", password = UserRepository.GetSHA512("test1", "test1"), email = "jorgeron1993@hotmail.com", genre = "Masculino", autonomous_community = "Andalucia", age = 21 });

            base.Seed(context);
        }
    }
}