# Test Task for MediaSoft

<a href="https://codeclimate.com/github/k0damaDEV/MediaSoft-Parcel-Tracker-TEST/maintainability"><img src="https://api.codeclimate.com/v1/badges/0f2d6d0661311cc97961/maintainability" /></a>
<a href="https://codeclimate.com/github/k0damaDEV/MediaSoft-Parcel-Tracker-TEST/test_coverage"><img src="https://api.codeclimate.com/v1/badges/0f2d6d0661311cc97961/test_coverage" /></a>

## API

### POST: /api/v1.0/register 

> Creates a new post shipment.
(JSON body example)
```
{
    "parcelType": "LETTER",
    "recipientIndex": 11201,
    "senderIndex": 22304,
    "recipientAddress": "4865 Redbud Drive",
    "recipientName": "Regina Wright",
    "postOffice": {
            "index": 22304,
            "name": "Alexandria VA",
            "address": "N.Pegram St. 22"
    }       
}
```



### GET /api/v1.0/{id}
> Returns information about shipment
```
{
    "id": 6,
    "parcelType": "LETTER",
    "recipientIndex": 11201,
    "senderIndex": 22304,
    "recipientAddress": "4865 Redbud Drive",
    "recipientName": "Regina Wright",
    "parcelStatus": "SHIPMENT_REGISTERED",
    "movementHistory": [
        {
            "id": 11,
            "index": 22304,
            "name": "Alexandria VA",
            "address": "N.Pegram St. 22"
        }
    ]
}
```




### PATCH /api/v1.0/{id}/intermediate
> Adding intermediate post station to movement history(when arrived) and changes status to "Arrived to intermediate post office"
(JSON body example)
```
{
    "index": 33254,
    "name": "Some Post Name",
    "address": "Some St. 321"
}
```




### PATCH /api/v1.0/{id}/leave
> Change parcel status to "Leaved from intermediate post office"




### PATCH /api/v1.0/{id}/received
> Change parcel status to "Received by recipient"




### Response body example with added intermediate post office

```
{
    "id": 6,
    "parcelType": "LETTER",
    "recipientIndex": 11201,
    "senderIndex": 22304,
    "recipientAddress": "4865 Redbud Drive",
    "recipientName": "Regina Wright",
    "parcelStatus": "ARRIVED_AT_INTERMEDIATE_POST_OFFICE",
    "movementHistory": [
        {
            "id": 11,
            "index": 22304,
            "name": "Alexandria VA",
            "address": "N.Pegram St. 22"
        },
        {
            "id": 12,
            "index": 33254,
            "name": "Some Post Name",
            "address": "Some St. 321"
        }
    ]
}
```
