using Microsoft.VisualStudio.TestTools.UnitTesting;
using Authentication.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Authentication.Repositories.Tests
{
    [TestClass()]
    public class UserRepositoryTests
    {
        [TestMethod()]
        public void GetSHA512Test()
        {
            //arrange
            var text = "test";
            var hexExpected = "EE26B0DD4AF7E749AA1A8EE3C10AE9923F618980772E473F8819A5D4940E0DB27AC185F8A0E1D5F84F88BC887FD67B143732C304CC5FA9AD8E6F57F50028A8FF";

            //act
           var hash= UserRepository.GetSHA512(text,"");
             hash = hash.Replace("-", string.Empty);


            Assert.IsTrue(hash == hexExpected);
        }
    }
}