use reqwest::Client;

#[tokio::main]
async fn main() {
  make_request().await;
}

async fn make_request() {
  let url: &str = "https://www.httpbin.org/get";

  let client: Client = Client::new();
  let result: Result<reqwest::Response, reqwest::Error> = client.get(url).send().await;

  if let Ok(response) = result {
    println!("{}", response.text().await.unwrap());
  } else {
    eprintln!("Failed to fetch URL: {}", result.err().unwrap());
  }
}
