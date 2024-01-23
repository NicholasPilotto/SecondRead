use fake::faker::internet::en::FreeEmail;
use fake::faker::lorem::en::Word;
use fake::faker::name::en::FirstName;
use fake::faker::name::en::LastName;
use fake::faker::phone_number::en::CellNumber;
use fake::Fake;
use reqwest::Client;
use std::env;
use std::io;

pub struct Configuration {
  url: String,
}
use serde::{Deserialize, Serialize};

#[derive(Serialize, Deserialize)]
struct User {
  firstName: String,
  lastName: String,
  birthDate: String,
  phoneNumber: String,
  email: String,
  password: String,
  role: i32,
}

#[tokio::main]
async fn main() {
  println!("Insert the number of request you want to perform: ");
  let mut input: String = String::new();

  io::stdin().read_line(&mut input).unwrap();

  let n_requests: i32 = input.trim().parse().expect("Input is not a valid number");

  read_configuration();
  make_request(n_requests).await;
}

fn read_configuration() -> Configuration {
  let key = "URL";
  let mut conf: Configuration = Configuration { url: String::new() };

  match env::var(key) {
    Ok(val) => conf.url = val,
    Err(e) => println!("couldn't interpret {}: {}", key, e),
  }

  return conf;
}

async fn make_request(n_requests: i32) {
  let conf: Configuration = read_configuration();

  let url: &str = &conf.url;

  let client: Client = Client::new();

  let mut i = 0;
  while i < n_requests {

    let user: User = User {
      firstName: FirstName().fake(),
      lastName: LastName().fake(),
      birthDate: Date().fake(),
      phoneNumber: CellNumber().fake(),
      email: FreeEmail().fake(),
      password: Word().fake(),
      role: 3,
    };

    println!("{}", serde_json::to_string(&user).unwrap());

    let result: Result<reqwest::Response, reqwest::Error> = client
      .post(url)
      .body(serde_json::to_string(&user).unwrap())
      .bearer_auth("")
      .header("Content-type", "application/json")
      .send()
      .await;


    if let Ok(response) = result {
      println!("{}", response.status());
      println!("{}", response.text().await.unwrap());
    } else {
      eprintln!("Failed to fetch URL: {}", result.err().unwrap());
    }
    i += 1;
  }
}
