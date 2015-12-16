﻿using Newtonsoft.Json;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Web.Mvc;

namespace Authentication.Models
{

    /// <summary>
    /// Esta clase representa la entidad usuario del sistema, 
    /// el cual dispone de una una id, username,password, email, genre, autonomous_community y age con sus correspondientes restricciones en forma de anotaciones
    /// 
    /// </summary>
    public class User
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int u_id { get; set; }

        public string username { get; set; }
        [JsonIgnore]
        public string password { get; set; }

        public string email { get; set; }

        public string genre { get; set; }

        public string autonomous_community { get; set; }

        public int age { get; set; }
    }
    /// <summary>
    /// Esta clase representra la entidad de registro, cuyos atributos con sus respectivas restricciones son necesarios para registrar un nuevo usuario al sistema.
    /// </summary>
    public class UserRegisterModel
    {

        [Required(ErrorMessage = "El nombre de usuario no puede estar vacío")]
        [Display(Name = "Nombre de usuario")]
        [MinLength(5, ErrorMessage = "El usuario debe tener al menos 5 caracteres")]
        [Unique]
        public string username { get; set; }

        [Required(ErrorMessage = "La contraseña no puede estar vacía")]
        [DataType(DataType.Password)]
        [Display(Name = "Contraseña")]
        [MinLength(5, ErrorMessage = "La contraseña debe tener al menos 5 caracteres")]
        public string password { get; set; }

        [Required(ErrorMessage = "La contraseña no puede estar vacía")]
        [System.ComponentModel.DataAnnotations.Compare("password", ErrorMessage = "La nueva contraseña y la contraseña de confirmación no coinciden.")]
        [DataType(DataType.Password)]
        [MinLength(5, ErrorMessage = "La contraseña debe tener al menos 5 caracteres")]
        [Display(Name = "Repite contraseña")]
        public string confirmPassword { get; set; }

        [Required(ErrorMessage = "El correo no puede estar vacío")]
        [DataType(DataType.EmailAddress)]
        [Display(Name = "Email")]
        [EmailAddress(ErrorMessage = "El email introducido no es válido")]
        [Unique]
        public string email { get; set; }

        [Required(ErrorMessage = "Debe elegir un género")]
        [RegularExpression(@"^((M|m)asculino|(F|f)emenino)$", ErrorMessage = "El género no es válido")]
        [Display(Name = "Género")]
        public string genre { get; set; }

        [Required(ErrorMessage = "Debe elegir una comunidad autónoma")]
        [AutonomousCommunity(ErrorMessage = "La comunidad autónoma no es válida")]
        [Display(Name = "Comunidad autónoma")]
        public string autonomous_community { get; set; }
        public IEnumerable<SelectListItem> Communities
        {
            get
            {
                yield return new SelectListItem { Text = "Andalucía" };
                yield return new SelectListItem { Text = "Murcia" };
                yield return new SelectListItem { Text = "Extremadura" };
                yield return new SelectListItem { Text = "Castilla la Mancha" };
                yield return new SelectListItem { Text = "Comunidad Valenciana" };
                yield return new SelectListItem { Text = "Castilla y León" };
                yield return new SelectListItem { Text = "Aragón" };
                yield return new SelectListItem { Text = "Cataluña" };
                yield return new SelectListItem { Text = "La Rioja" };
                yield return new SelectListItem { Text = "Galicia" };
                yield return new SelectListItem { Text = "Asturias" };
                yield return new SelectListItem { Text = "Cantabria" };
                yield return new SelectListItem { Text = "País Vasco" };
                yield return new SelectListItem { Text = "Navarra" };
                yield return new SelectListItem { Text = "Canarias" };
                yield return new SelectListItem { Text = "Islas Baleares" };
                yield return new SelectListItem { Text = "Ceuta" };
                yield return new SelectListItem { Text = "Melilla" };
            }
        }

        [Required(ErrorMessage = "La edad no puede estar vacía")]
        [Range(1, 200, ErrorMessage = "La edad debe estar entre 1 y 200")]
        [Display(Name = "Edad")]
        public int? age { get; set; }
    }
    /// <summary>
    /// Esta clase representa la entidad de logeo de usuario, en ella se indican los atributos necesarios para logearse, junto con sus respectivas restricciones a la hora de validar.
    /// </summary>
    public class UserLoginModel
    {

        [Required(ErrorMessage = "El nombre de usuario no puede estar vacío")]
        [Display(Name = "Nombre de usuario")]
        [MinLength(5, ErrorMessage = "El usuario debe tener al menos 5 caracteres")]
        public string username { get; set; }

        [Required(ErrorMessage = "La contraseña no puede estar vacía")]
        [DataType(DataType.Password)]
        [Display(Name = "Contraseña")]
        [MinLength(5, ErrorMessage = "La contraseña debe tener al menos 5 caracteres")]
        public string password { get; set; }
    }
}