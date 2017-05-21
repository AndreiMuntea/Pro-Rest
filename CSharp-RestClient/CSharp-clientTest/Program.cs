using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Http;
using System.Net.Http.Formatting;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;


namespace CSharp_clientTest
{
   class Program
   {
      static HttpClient _client = new HttpClient();
      static String defaultPath = "http://localhost:8080/trials";
      private static String jSonType = "application/json";

      static void Main(string[] args)
      {
         _client.BaseAddress = new Uri(defaultPath);
         _client.DefaultRequestHeaders.Accept.Clear();
         _client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

         RunAsync().Wait();
      }

      static async Task RunAsync()
      {
         List<Trial> list = new List<Trial>();
         list = await GetAllAsync(defaultPath);
         list.ForEach(Console.WriteLine);

         Trial t = new Trial {difficulty = 5, name = "new trial inserted", id = "new trial inserted"};
         await InsertTrialAsync(defaultPath, t);

         list = await GetAllAsync(defaultPath);
         list.ForEach(Console.WriteLine);

         t.difficulty = 10000;
         await UpdateTrialAsync(defaultPath, t);

         list = await GetAllAsync(defaultPath);
         list.ForEach(Console.WriteLine);

         await DeleteTrialAsync(defaultPath, t.name);
         list = await GetAllAsync(defaultPath);
         list.ForEach(Console.WriteLine);

      }

      static async Task<Trial> GetTrialAsync(string path, string trialId)
      {
         Trial product = null;
         var response = await _client.GetAsync(path + "/" + trialId);
         if (response.IsSuccessStatusCode)
         {
            var jsonContent = response.Content.ReadAsStringAsync().Result;
            product = JsonConvert.DeserializeObject<Trial>(jsonContent);
         }
         return product;
      }

      static async Task InsertTrialAsync(string path, Trial t)
      {
         var json = JsonConvert.SerializeObject(t);
         var response = await _client.PostAsync(path, new StringContent(json, Encoding.UTF8, "application/json"));
         Console.WriteLine(response.IsSuccessStatusCode ? "Trial Inserted!" : "ERROR!");
      }

      static async Task DeleteTrialAsync(string path, string trialId)
      {
         var response = await _client.DeleteAsync(path + "/" + trialId);
         Console.WriteLine(response.IsSuccessStatusCode ? "Trial Deleted!" : "ERROR!");
      }

      static async Task UpdateTrialAsync(string path, Trial t)
      {
         var json = JsonConvert.SerializeObject(t);
         var response = await _client.PutAsync(path, new StringContent(json, Encoding.UTF8, "application/json"));
         Console.WriteLine(response.IsSuccessStatusCode ? "Trial Updated!" : "ERROR!");
      }

      static async Task<List<Trial>> GetAllAsync(string path)
      {
         var response = await _client.GetAsync(path);
         List<Trial> trials = new List<Trial>();

         if (response.IsSuccessStatusCode)
         {
            var jsonContent = response.Content.ReadAsStringAsync().Result;
            trials = JsonConvert.DeserializeObject<List<Trial>>(jsonContent);
         }

         return trials;
      }
   }
}
