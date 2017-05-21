using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSharp_clientTest
{
    class Trial
    {

       public string name { get; set; }
      public string id { get; set; }
      public Int32 difficulty { get; set; }

       public Trial()
       {
       }

       public override string ToString()
       {
          return $"{nameof(name)}: {name}, {nameof(id)}: {id}, {nameof(difficulty)}: {difficulty}";
       }
    }
}
