use reqwest::Client;
use std::env;

pub struct Configuration {
  url: String,
}

#[tokio::main]
async fn main() {
  read_configuration();
  make_request().await;
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

async fn make_request() {
  let conf: Configuration = read_configuration();

  let url: &str = &conf.url;

  let client: Client = Client::new();
  let result: Result<reqwest::Response, reqwest::Error> = client.get(url).send().await;

  if let Ok(response) = result {
    println!("{}", response.text().await.unwrap());
  } else {
    eprintln!("Failed to fetch URL: {}", result.err().unwrap());
  }
}
